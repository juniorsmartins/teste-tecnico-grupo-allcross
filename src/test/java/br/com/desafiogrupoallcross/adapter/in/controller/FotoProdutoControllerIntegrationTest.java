package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integração - FotoProduto Controller - Cadastrar")
class FotoProdutoControllerIntegrationTest {

    private static final String END_POINT = "/api/v1/produtos";

    @Autowired
    private WebTestClient webTestClient;

    @Nested
    @DisplayName("Foto válida")
    class FotoProdutoValida {

        @Autowired
        private FotoProdutoRepository fotoProdutoRepository;

        @Autowired
        private ProdutoRepository produtoRepository;

        // Carregue uma imagem de exemplo do classpath
        private ClassPathResource imagem;

        private ProdutoEntity produtoSalvo;

        @BeforeEach
        void criarCenario() throws IOException {
            var produto = FabricaDeObjetosDeTeste.gerarProdutoEntityBuilder().build();
            produtoSalvo = produtoRepository.save(produto);

            imagem = new ClassPathResource("teste.jpg");
        }

        @Test
        @DisplayName("completa")
        void dadoFotoValida_QuandoCadastrarFoto_EntaoRetornarHttp204NoContent() throws IOException {

            // Simule uma requisição multipart
            webTestClient.post()
                .uri("/api/v1/produtos/" + produtoSalvo.getId() + "/imagem")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("foto", imagem).with("descricao", "descrição da foto"))
                .exchange()
                .expectStatus().isNoContent();
        }

        @Test
        @DisplayName("persistência")
        void dadoFotoValida_QuandoCadastrarFoto_EntaoRetornarFotoPersistida() throws IOException {
            var produtoId = produtoSalvo.getId();

            // Simule uma requisição multipart
            webTestClient.post()
                    .uri("/api/v1/produtos/" + produtoId + "/imagem")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData("foto", imagem)
                            .with("descricao", "descrição X"))
                    .exchange()
                    .expectStatus().isNoContent();

            var fotoPersistida = fotoProdutoRepository.findById(produtoId).get();

            Assertions.assertEquals("descrição X", fotoPersistida.getDescricao());
            Assertions.assertEquals("teste.jpg", fotoPersistida.getNome());
            Assertions.assertEquals("image/jpeg", fotoPersistida.getTipo());
            Assertions.assertTrue(fotoPersistida.getFoto().length > 0);
        }
    }
}