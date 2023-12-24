package br.com.desafiogrupoallcross.application.core.domain;

import br.com.desafiogrupoallcross.application.core.usecase.ProdutoCadastrarUseCase;
import br.com.desafiogrupoallcross.config.exception.http_400.CampoNuloProibidoException;
import br.com.desafiogrupoallcross.config.exception.http_400.CampoVazioProibidoException;
import br.com.desafiogrupoallcross.config.exception.http_400.DadoComTamanhoMaximoInvalidoException;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Classe ProdutoBusiness")
class ProdutoBusinessUnitTest {

    private ProdutoCadastrarUseCase cadastrarUseCase;

    private ProdutoBusiness produtoBusiness;

    @BeforeEach
    void criarCenario() {
        produtoBusiness = FabricaDeObjetosDeTeste.gerarProdutoBusiness();
    }

    @Nested
    @DisplayName("Campo Nome")
    class Chapeu {

        @Test
        @DisplayName("nulo")
        void dadoNomeNulo_QuandoSetar_EntaoLancarException() {
            Executable acao = () -> produtoBusiness.setNome(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio ou em branco")
        void dadoNomeVazioOuEmBranco_QuandoSetar_EntaoLancarException(String nome) {
            Executable acao = () -> produtoBusiness.setNome(nome);
            Assertions.assertThrows(CampoVazioProibidoException.class, acao);
        }

        @Test
        @DisplayName("com tamanho inválido")
        void dadoNomeComTamanhoInvalido_QuandoSetar_EntaoLancarException() {
            var nome = FabricaDeObjetosDeTeste.faker.lorem().characters(101, 120);
            Executable acao = () -> produtoBusiness.setNome(nome);
            Assertions.assertThrows(DadoComTamanhoMaximoInvalidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Valor Custo")
    class ValorCusto {

        @Test
        @DisplayName("nulo")
        void dadoValorCustoNulo_QuandoSetar_EntaoLancarException() {
            Executable acao = () -> produtoBusiness.setValorCusto(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Valor Venda")
    class ValorVenda {

        @Test
        @DisplayName("nulo")
        void dadoValorVendaNulo_QuandoSetar_EntaoLancarException() {
            Executable acao = () -> produtoBusiness.setValorVenda(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }
    }
}

