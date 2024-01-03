package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.config.exception.ApiError;
import br.com.desafiogrupoallcross.config.exception.TipoDeErroEnum;
import br.com.desafiogrupoallcross.utilitarios.JwtAuthentication;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.IOException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/produtos/produtos-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/produtos/produtos-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integração - FotoProduto Controller - Cadastrar")
class FotoProdutoControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/produtos/fotos";

    public static final String USERNAME_ADMIN = "kent_beck@email.com";

    public static final String PASSWORD_ADMIN = "0123456789";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private FotoProdutoRepository fotoProdutoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private ClassPathResource imagem;

    @BeforeEach
    void criarCenario() {
        imagem = new ClassPathResource("teste.jpg"); // Carregue imagem para teste - está no diretório Resources
    }

    @AfterEach
    void destruirCenario() {
        this.fotoProdutoRepository.deleteAll();
        this.produtoRepository.deleteAll();
    }

    @Nested
    @DisplayName("Foto válida")
    class FotoProdutoValida {

        private ProdutoEntity produtoSalvo;

        @BeforeEach
        void criarCenario() {
//            var produto = FabricaDeObjetosDeTeste.gerarProdutoEntityBuilder().build();
            produtoSalvo = produtoRepository.findById(2001L).get();
        }

        @AfterEach
        void destruirCenario() {
            fotoProdutoRepository.deleteAll();
            produtoRepository.deleteAll();
        }

        @Test
        @DisplayName("completa")
        void dadoFotoValida_QuandoArmazenarFoto_EntaoRetornarHttp204NoContent() throws IOException {

            // Simule uma requisição multipart
            webTestClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(END_POINT)
                        .queryParam("produtoId", 2001L)
                        .queryParam("foto", imagem)
                        .build())
                .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, USERNAME_ADMIN, PASSWORD_ADMIN))
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .exchange()
                .expectStatus().isNoContent();
        }
        //                .body(BodyInserters.fromMultipartData("foto", imagem).with("descricao", "descrição da foto"))
        @Test
        @DisplayName("persistência")
        void dadoFotoValida_QuandoCadastrarFoto_EntaoRetornarFotoPersistida() {
            var produtoId = produtoSalvo.getId();

            // Simule uma requisição multipart
            webTestClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path(END_POINT)
                            .queryParam("produtoId", 2001L)
                            .queryParam("foto", imagem)
                            .build())
                    .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, USERNAME_ADMIN, PASSWORD_ADMIN))
                    .contentType(MediaType.MULTIPART_FORM_DATA)
//                    .body(BodyInserters.fromMultipartData("foto", imagem)
//                            .with("descricao", "descrição X"))
                    .exchange()
                    .expectStatus().isNoContent();

            var fotoPersistida = fotoProdutoRepository.findByProdutoId(produtoId).get(0);

            Assertions.assertNotNull(fotoPersistida.getProduto());
            Assertions.assertEquals("teste.jpg", fotoPersistida.getNome());
            Assertions.assertEquals("image/jpeg", fotoPersistida.getTipo());
            Assertions.assertTrue(fotoPersistida.getTamanho() > 0);
            Assertions.assertTrue(fotoPersistida.getFoto().length > 0);
        }

        @Test
        @DisplayName("persistência dobrada")
        void dadoDuasFotosValidas_QuandoCadastrarFoto_EntaoRetornarDuasFotosPersistidas() {
            var produtoId = produtoSalvo.getId();

            // Simule uma requisição multipart
            webTestClient.post()
                    .uri(END_POINT + produtoId + "/imagem")
                    .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, USERNAME_ADMIN, PASSWORD_ADMIN))
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData("foto", imagem)
                            .with("descricao", "descrição X"))
                    .exchange()
                    .expectStatus().isNoContent();

            var fotoPersistida = fotoProdutoRepository.findByProdutoId(produtoId).get(0);

            Assertions.assertNotNull(fotoPersistida.getProduto());
            Assertions.assertEquals("teste.jpg", fotoPersistida.getNome());
            Assertions.assertEquals("image/jpeg", fotoPersistida.getTipo());
            Assertions.assertTrue(fotoPersistida.getTamanho() > 0);
            Assertions.assertTrue(fotoPersistida.getFoto().length > 0);

            webTestClient.post()
                    .uri(END_POINT + produtoId + "/imagem")
                    .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, USERNAME_ADMIN, PASSWORD_ADMIN))
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData("foto", imagem)
                            .with("descricao", "descrição Y"))
                    .exchange()
                    .expectStatus().isNoContent();

            var fotoPersistida2 = fotoProdutoRepository.findByProdutoId(produtoId);

            Assertions.assertNotNull(fotoPersistida.getProduto());
            Assertions.assertEquals("teste.jpg", fotoPersistida2.get(1).getNome());
            Assertions.assertEquals("image/jpeg", fotoPersistida2.get(1).getTipo());
            Assertions.assertTrue(fotoPersistida.getTamanho() > 0);
            Assertions.assertTrue(fotoPersistida2.get(1).getFoto().length > 0);
            Assertions.assertEquals(2, fotoPersistida2.size());
        }
    }

    @Nested
    @DisplayName("Exceções")
    class FotoProdutoException {

        @Test
        @DisplayName("com produto inexistente")
        void dadoFotoProdutoComProdutoInexistente_QuandoCadastrar_EntaoLancarException() {
            var produtoIdInexistente = 0L;

            var resposta = webTestClient.post()
                    .uri(END_POINT + produtoIdInexistente + "/imagem")
                    .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, USERNAME_ADMIN, PASSWORD_ADMIN))
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData("foto", imagem)
                            .with("descricao", "descrição X"))
                    .exchange()
                    .expectStatus().isNotFound()
                    .expectBody(ApiError.class)
                    .returnResult().getResponseBody();

            Assertions.assertEquals(404, resposta.getStatus());
            Assertions.assertEquals(TipoDeErroEnum.RECURSO_NAO_ENCONTRADO.getTitulo(), resposta.getTitle());
        }
    }
}