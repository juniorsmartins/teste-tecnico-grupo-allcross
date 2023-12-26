package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoCadastrarDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoCadastrarDtoOut;
import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integração - Produto Controller - Cadastrar")
class ProdutoControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/produtos";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoProdutoRepository fotoProdutoRepository;

    private ProdutoEntity primeiroProduto;

    private ProdutoEntity segundoProduto;

    private ProdutoEntity terceiroProduto;

    private ProdutoCadastrarDtoIn dtoIn;

    @BeforeEach
    void criarCenario() {
        primeiroProduto = FabricaDeObjetosDeTeste.gerarProdutoEntityBuilder().build();
        segundoProduto = FabricaDeObjetosDeTeste.gerarProdutoEntityBuilder().build();
        terceiroProduto = FabricaDeObjetosDeTeste.gerarProdutoEntityBuilder().build();

        primeiroProduto = this.produtoRepository.save(primeiroProduto);
        segundoProduto = this.produtoRepository.save(segundoProduto);
        terceiroProduto = this.produtoRepository.save(terceiroProduto);

        dtoIn = FabricaDeObjetosDeTeste.gerarProdutoCadastrarDtoIn();
    }

    @AfterEach
    void destruirCenario() {
        this.fotoProdutoRepository.deleteAll();
        this.produtoRepository.deleteAll();
    }

    @Nested
    @DisplayName("Cadastrar com Dados válidos")
    class ProdutoCadastrarComDadoValido {

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
    class ProdutoPesquisarComDadoValido {

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
        @DisplayName("com filtro de nome e sem paginação")
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
                    .jsonPath("$.content[0].nome").isEqualTo(primeiroProduto.getNome());
        }

        @Test
        @DisplayName("com filtro de dois nomes e sem paginação")
        void dadoComFiltroDeDoisNomesAndSemPaginacao_QuandoPesquisar_EntaoRetornarListaComDoisProdutos() {
            var doisNomesSeparadorPorVirgula = primeiroProduto.getNome() + "," + terceiroProduto.getNome();

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
                    .jsonPath("$.content[0].nome").isEqualTo(primeiroProduto.getNome())
                    .jsonPath("$.content[1].nome").isEqualTo(terceiroProduto.getNome());
        }
    }
}

