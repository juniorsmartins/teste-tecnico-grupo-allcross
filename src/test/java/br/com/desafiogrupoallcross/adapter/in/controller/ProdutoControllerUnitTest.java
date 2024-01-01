package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoCadastrarDtoIn;
import br.com.desafiogrupoallcross.application.port.in.ProdutoCadastrarInputPort;
import br.com.desafiogrupoallcross.application.port.in.ProdutoDeletarInputPort;
import br.com.desafiogrupoallcross.config.exception.ApiError;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import br.com.desafiogrupoallcross.utilitarios.JwtAuthentication;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DisplayName("Unitário - Produto Controller - Deletar")
class ProdutoControllerUnitTest {

    private static final String END_POINT = "/api/v1/produtos";

    public static final String USERNAME_ADMIN = "kent_beck@email.com";

    public static final String PASSWORD_ADMIN = "0123456789";

    public static Faker faker = new Faker();

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProdutoCadastrarInputPort cadastrarInputPort;

    @InjectMocks
    private ProdutoController controller;

    @Nested
    @DisplayName("Cadastrar")
    class CadastrarException {

        private ProdutoCadastrarDtoIn.ProdutoCadastrarDtoInBuilder cadastrarDtoInBuilder;

        @BeforeEach
        void criarCenario() {
            cadastrarDtoInBuilder = FabricaDeObjetosDeTeste.gerarProdutoCadastrarDtoInBuilder();
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        @DisplayName("com nome vazio e em branco")
        void dadoProdutoCadastrarDtoInVazioAndEmBranco_QuandoCadastrar_EntaoLancarException(String nome) {
            var paraVerificar = cadastrarDtoInBuilder.nome(nome).build();

            var erro = webTestClient.post()
                    .uri(END_POINT)
                    .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, USERNAME_ADMIN, PASSWORD_ADMIN))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(paraVerificar)
                    .exchange()
                    .expectStatus().isBadRequest()
                    .expectBody(ApiError.class)
                    .returnResult().getResponseBody();

            Assertions.assertEquals(erro.getParametrosInvalidos().get(0).getLocalDeErro(), "nome");
            Assertions.assertEquals(erro.getParametrosInvalidos().get(0).getAnotacaoViolada(), "NotBlank");
            Mockito.verifyNoInteractions(cadastrarInputPort);
        }

        @Test
        @DisplayName("com nome de tamanho inválido")
        void dadoProdutoCadastrarDtoInComTamanhoDeNomeInvalido_QuandoCadastrar_EntaoLancarException() {
            var paraVerificar = cadastrarDtoInBuilder
                    .nome(faker.lorem().characters(101, 130)).build();

            var erro = webTestClient.post()
                    .uri(END_POINT)
                    .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, USERNAME_ADMIN, PASSWORD_ADMIN))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(paraVerificar)
                    .exchange()
                    .expectStatus().isBadRequest()
                    .expectBody(ApiError.class)
                    .returnResult().getResponseBody();

            Assertions.assertEquals(erro.getParametrosInvalidos().get(0).getLocalDeErro(), "nome");
            Assertions.assertEquals(erro.getParametrosInvalidos().get(0).getAnotacaoViolada(), "Size");
            Mockito.verifyNoInteractions(cadastrarInputPort);
        }
    }
}

