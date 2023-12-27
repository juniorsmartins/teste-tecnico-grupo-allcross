package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.in.dto.request.CategoriaId;
import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoAtualizarDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoCadastrarDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoCadastrarDtoOut;
import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = "/sql/produtos/produtos-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/produtos/produtos-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DisplayName("Integração - Produto Controller - Cadastrar")
class ProdutoControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/produtos";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoProdutoRepository fotoProdutoRepository;

    private ProdutoCadastrarDtoIn dtoIn;

    private ProdutoEntity primeiroProduto;

    private ProdutoEntity segundoProduto;

    private ProdutoEntity terceiroProduto;

    @BeforeEach
    void criarCenario() {
        dtoIn = FabricaDeObjetosDeTeste.gerarProdutoCadastrarDtoIn();

        primeiroProduto = produtoRepository.findById(2001L).get();
        segundoProduto = produtoRepository.findById(2002L).get();
        terceiroProduto = produtoRepository.findById(2003L).get();
    }

    @Nested
    @DisplayName("Cadastrar com dados válidos")
    class ProdutoCadastrar {

        @Test
        @DisplayName("completos por XML")
        void dadoProdutoValido_QuandoCadastrarComContentNegotiationXML_EntaoRetornarHttp201() {

            webTestClient.post()
                    .uri(END_POINT)
                    .accept(MediaType.APPLICATION_XML)
                    .bodyValue(dtoIn)
                    .exchange()
                    .expectStatus().isCreated()
                    .expectHeader().contentType(MediaType.APPLICATION_XML)
                    .expectBody().consumeWith(response -> {
                        assertThat(response.getResponseBody()).isNotNull();
                    });
        }

        @Test
        @DisplayName("completos por JSON")
        void dadoProdutoValido_QuandoCadastrarComContentNegotiationJSon_EntaoRetornarProdutoCadastrarDtoOutComDadosIguaisEntradaAndHttp201() {

            webTestClient.post()
                    .uri(END_POINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(dtoIn)
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody(ProdutoCadastrarDtoOut.class)
                    .consumeWith(response -> {
                        assertThat(response.getResponseBody()).isNotNull();
                        assertThat(response.getResponseBody().id()).isNotNull();
                        assertThat(response.getResponseBody().sku()).isNotNull();
                        assertThat(response.getResponseBody().nome()).isEqualTo(dtoIn.nome());
                        assertThat(response.getResponseBody().ativo()).isEqualTo(dtoIn.ativo());
                        assertThat(response.getResponseBody().valorCusto()).isEqualTo(dtoIn.valorCusto());
                        assertThat(response.getResponseBody().icms()).isEqualTo(dtoIn.icms());
                        assertThat(response.getResponseBody().valorVenda()).isEqualTo(dtoIn.valorVenda());
                        assertThat(response.getResponseBody().quantidadeEstoque()).isEqualTo(dtoIn.quantidadeEstoque());
                        assertThat(response.getResponseBody().categoria().id()).isEqualTo(dtoIn.categoria().id());
                        assertThat(response.getResponseBody().dataCadastro()).isNotNull();
                    });
        }
    }

    @Nested
    @DisplayName("Pesquisar")
    class ProdutoPesquisar {

        @Test
        @DisplayName("sem filtro e sem paginação")
        void dadoSemFiltroAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComTodosOsProdutos() {

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectHeader().contentType(MediaType.APPLICATION_JSON)
                    .expectBody()
                    .jsonPath("$.content.size()").isEqualTo(3);
        }

        @Test
        @DisplayName("sem filtro e com paginação")
        void dadoSemFiltroAndComPaginacao_QuandoPesquisar_EntaoRetornarListaComTodosOsProdutos() {
            var expectedNomesAscendentes = List.of(primeiroProduto.getNome(), terceiroProduto.getNome());

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("page", 0)
                            .queryParam("size", 2)
                            .queryParam("sort", "nome,asc")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectHeader().contentType(MediaType.APPLICATION_JSON)
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(2)
                    .jsonPath("$.content.size()").isEqualTo(2)
                    .jsonPath("$.content[0].nome").isEqualTo(expectedNomesAscendentes.get(0))
                    .jsonPath("$.content[1].nome").isEqualTo(expectedNomesAscendentes.get(1));
        }

        @Test
        @DisplayName("com um nome e sem paginação")
        void dadoComFiltroDeNomeAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var nomePesquisado = primeiroProduto.getNome();

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("nome", nomePesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].nome").isEqualTo(nomePesquisado);
        }

        @Test
        @DisplayName("com dois nomes e sem paginação")
        void dadoComFiltroDeDoisNomesAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComDoisProdutos() {
            var doisNomesSeparadorPorVirgula = primeiroProduto.getNome() + "," + terceiroProduto.getNome();

            var expected = List.of(doisNomesSeparadorPorVirgula.split(","));

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("nome", doisNomesSeparadorPorVirgula)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(2)
                    .jsonPath("$.content[*].nome").value(Matchers.containsInAnyOrder(expected.toArray()));
        }

        @Test
        @DisplayName("com um id e sem paginação")
        void dadoComFiltroDeIdAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var idPesquisado = segundoProduto.getId();

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("id", idPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].id").isEqualTo(idPesquisado);
        }

        @Test
        @DisplayName("com dois ids e sem paginação")
        void dadoComFiltroDeDoisIdsAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComDoisProdutos() {
            var doisIdsSeparadorPorVirgula = segundoProduto.getId() + "," + terceiroProduto.getId();

            var expected = List.of(doisIdsSeparadorPorVirgula.split(","));

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("id", doisIdsSeparadorPorVirgula)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(2)
                    .jsonPath("$.content[*].id", Matchers.containsInAnyOrder(expected.toArray()));
        }

        @Test
        @DisplayName("com sku e sem paginação")
        void dadoComFiltroDeSkuAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var valorPesquisado = segundoProduto.getSku();

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("sku", valorPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[*].sku").isEqualTo(valorPesquisado.toString());
        }

        @Test
        @DisplayName("com um ativo e sem paginação")
        void dadoComFiltroDeAtivoAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var ativoPesquisado = segundoProduto.isAtivo();

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("ativo", ativoPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].ativo").isEqualTo(ativoPesquisado);
        }

        @Test
        @DisplayName("com um valorCusto e sem paginação")
        void dadoComFiltroDeValorCustoAndSemPaginacao_QuandoPesquisar_EntaoRetornarUmProduto() {
            var valorPesquisado = segundoProduto.getValorCusto();

            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("valorCusto", valorPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].valorCusto").isEqualTo(valorPesquisado.setScale(1, BigDecimal.ROUND_HALF_UP));
        }

        @Test
        @DisplayName("com um icms e sem paginação")
        void dadoComFiltroDeIcmsAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var valorPesquisado = segundoProduto.getIcms();

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("icms", valorPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].icms").isEqualTo(valorPesquisado);
        }

        @Test
        @DisplayName("com um valorVenda e sem paginação")
        void dadoComFiltroDeValorVendaAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var valorPesquisado = terceiroProduto.getValorVenda();

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("valorVenda", valorPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].valorVenda").isEqualTo(valorPesquisado.setScale(1, BigDecimal.ROUND_HALF_UP));
        }

        @Test
        @DisplayName("com um quantidadeEstoque e sem paginação")
        void dadoComFiltroDeQuantidadeEstoqueAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var valorPesquisado = primeiroProduto.getQuantidadeEstoque();

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("quantidadeEstoque", valorPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].quantidadeEstoque").isEqualTo(valorPesquisado);
        }

        @Test
        @DisplayName("com um categoria.id e sem paginação")
        void dadoComFiltroDeCategoriaIdAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var valorPesquisado = segundoProduto.getCategoria().getId();

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("categoria.id", valorPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].categoria.id").isEqualTo(valorPesquisado);
        }

        @Test
        @DisplayName("com dois categoria.id e sem paginação")
        void dadoComFiltroComDuasCategoriaIdAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComDoisProduto() {
            var doisIdsSeparadorPorVirgula = segundoProduto.getCategoria().getId() + "," + terceiroProduto.getCategoria().getId();

            var expected = List.of(doisIdsSeparadorPorVirgula.split(","));

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("categoria.id", doisIdsSeparadorPorVirgula)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(2)
                    .jsonPath("$.content[*].categoria.id", Matchers.containsInAnyOrder(expected.toArray()));
        }

        @Test
        @DisplayName("com um categoria.nome e sem paginação")
        void dadoComFiltroDeCategoriaNomeAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var valorPesquisado = segundoProduto.getCategoria().getNome();

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("categoria.nome", valorPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].categoria.nome").isEqualTo(valorPesquisado);
        }

        @Test
        @DisplayName("com dois categoria.nome e sem paginação")
        void dadoComFiltroComDuasCategoriaNomeAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComDoisProduto() {
            var doisIdsSeparadorPorVirgula = segundoProduto.getCategoria().getNome() + "," + terceiroProduto.getCategoria().getNome();

            var expected = List.of(doisIdsSeparadorPorVirgula.split(","));

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("categoria.nome", doisIdsSeparadorPorVirgula)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(2)
                    .jsonPath("$.content[*].categoria.nome", Matchers.containsInAnyOrder(expected.toArray()));
        }

        @Test
        @DisplayName("com um categoria.ativo e sem paginação")
        void dadoComFiltroDeCategoriaAtivoAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var valorPesquisado = false;

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("categoria.ativo", valorPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].categoria.ativo").isEqualTo(valorPesquisado);
        }

        @Test
        @DisplayName("com um categoria.tipo e sem paginação")
        void dadoComFiltroDeCategoriaTipoAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var valorPesquisado = segundoProduto.getCategoria().getTipo().toString();

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("categoria.tipo", valorPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[0].categoria.tipo").isEqualTo(valorPesquisado);
        }

        @Test
        @DisplayName("com dois categoria.tipo e sem paginação")
        void dadoComFiltroComDoisCategoriaTipoAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComDoisProduto() {
            var doisTiposSeparadorPorVirgula = segundoProduto.getCategoria().getTipo() + "," + terceiroProduto.getCategoria().getTipo();

            var expected = List.of(doisTiposSeparadorPorVirgula.split(","));

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("categoria.tipo", doisTiposSeparadorPorVirgula)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(2)
                    .jsonPath("$.content[*].categoria.tipo", Matchers.containsInAnyOrder(expected.toArray()));
        }

        @Test
        @DisplayName("com dataCadastro e sem paginação")
        void dadoComFiltroDeDataCadastroAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComUmProduto() {
            var valorPesquisado = "01/01/2022";

            webTestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("dataCadastro", valorPesquisado)
                            .queryParam("paginacao", "")
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.totalPages").isEqualTo(1)
                    .jsonPath("$.totalElements").isEqualTo(1)
                    .jsonPath("$.content[*].nome").isEqualTo("Notebook");
        }
    }

    @Nested
    @DisplayName("Inverter status ativo")
    class ProdutoInverterStatusAtivo {

        @Test
        @DisplayName("inativar")
        void dadoIdDeProdutoValidoAtivo_QuandoInativar_EntaoRetornarProdutoInativado() {
            var produtoId = primeiroProduto.getId();
            var statusAtivo = primeiroProduto.isAtivo();

            Assertions.assertTrue(statusAtivo);

            webTestClient.patch()
                    .uri(END_POINT + "/" + produtoId + "/inverter-status-ativo")
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(ProdutoCadastrarDtoOut.class)
                    .consumeWith(response -> {
                        assertThat(response.getResponseBody().id()).isEqualTo(produtoId);
                        assertThat(response.getResponseBody().ativo()).isNotEqualTo(statusAtivo);
                    });
        }

        @Test
        @DisplayName("ativar")
        void dadoIdDeProdutoValidoInativo_QuandoAtivar_EntaoRetornarProdutoAtivado() {
            var produtoId = segundoProduto.getId();
            var statusAtivo = segundoProduto.isAtivo();

            Assertions.assertFalse(statusAtivo);

            webTestClient.patch()
                    .uri(END_POINT + "/" + produtoId + "/inverter-status-ativo")
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(ProdutoCadastrarDtoOut.class)
                    .consumeWith(response -> {
                        assertThat(response.getResponseBody().id()).isEqualTo(produtoId);
                        assertThat(response.getResponseBody().ativo()).isNotEqualTo(statusAtivo);
                    });
        }
    }

    @Nested
    @DisplayName("Deletar")
    class ProdutoDeletar {

        @Test
        @DisplayName("por id")
        void dadoIdDeProduto_QuandoDeletar_EntaoRetornarHttp204NoContent() {
            var produtoId = primeiroProduto.getId();

            webTestClient.delete()
                    .uri(END_POINT + "/" + produtoId)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isNoContent()
                    .expectBody();
        }

        @Test
        @DisplayName("removido por id")
        void dadoIdDeProduto_QuandoDeletar_EntaoRemoverProdutoDoBancoDeDados() {
            var produtoId = segundoProduto.getId();

            webTestClient.delete()
                    .uri(END_POINT + "/" + produtoId)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isNoContent()
                    .expectBody();

            var resposta = produtoRepository.findById(produtoId);
            Assertions.assertTrue(resposta.isEmpty());
        }
    }

    @Nested
    @DisplayName("Atualizar")
    class ProdutoAtualizar {

        private ProdutoAtualizarDtoIn atualizarDtoIn;

        @BeforeEach
        void criarCenario() {
            atualizarDtoIn = new ProdutoAtualizarDtoIn(primeiroProduto.getId(),
                    primeiroProduto.getNome(), primeiroProduto.isAtivo(), primeiroProduto.getValorCusto(),
                    primeiroProduto.getIcms(), primeiroProduto.getValorVenda(), primeiroProduto.getQuantidadeEstoque(),
                    new CategoriaId(primeiroProduto.getCategoria().getId()));
        }

        @Test
        @DisplayName("completos por XML")
        void dadoProdutoValido_QuandoAtualizarComContentNegotiationXML_EntaoRetornarHttp200() {

            webTestClient.put()
                    .uri(END_POINT)
                    .accept(MediaType.APPLICATION_XML)
                    .bodyValue(atualizarDtoIn)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(ProdutoCadastrarDtoOut.class)
                    .consumeWith(response -> {
                        assertThat(response.getResponseBody()).isNotNull();
                    });
        }

        @Test
        @DisplayName("completos por JSON")
        void dadoProdutoValido_QuandoAtualizarComContentNegotiationJSon_EntaoRetornarProdutoCadastrarDtoOutComDadosIguaisEntradaAndHttp200() {

            webTestClient.put()
                    .uri(END_POINT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(atualizarDtoIn)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(ProdutoCadastrarDtoOut.class)
                    .consumeWith(response -> {
                        assertThat(response.getResponseBody().sku()).isEqualTo(primeiroProduto.getSku());
                        assertThat(response.getResponseBody().nome()).isEqualTo(atualizarDtoIn.nome());
                        assertThat(response.getResponseBody().ativo()).isEqualTo(atualizarDtoIn.ativo());
                        assertThat(response.getResponseBody().valorCusto()).isEqualTo(atualizarDtoIn.valorCusto());
                        assertThat(response.getResponseBody().icms()).isEqualTo(atualizarDtoIn.icms());
                        assertThat(response.getResponseBody().valorVenda()).isEqualTo(atualizarDtoIn.valorVenda());
                        assertThat(response.getResponseBody().quantidadeEstoque()).isEqualTo(atualizarDtoIn.quantidadeEstoque());
                        assertThat(response.getResponseBody().categoria().id()).isEqualTo(atualizarDtoIn.categoria().id());
                        assertThat(response.getResponseBody().dataCadastro()).isNotNull();
                        assertThat(response.getResponseBody().dataAtualizacao()).isNotNull();
                    });
        }
    }
}

