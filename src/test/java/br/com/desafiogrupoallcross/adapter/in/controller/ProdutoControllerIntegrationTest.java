package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoCadastrarDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoCadastrarDtoOut;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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

    private ProdutoCadastrarDtoIn dtoIn;

    @BeforeEach
    void criarCenario() {
        dtoIn = FabricaDeObjetosDeTeste.gerarProdutoCadastrarDtoIn();
    }

    @Nested
    @DisplayName("Dados válidos")
    class DadoValido {

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
                        assertThat(response.getResponseBody().dataCadastro()).isNotNull();
                    });
        }
    }
}

