package br.com.desafiogrupoallcross.application.core.domain;

import br.com.desafiogrupoallcross.application.core.usecase.ProdutoCadastrarUseCase;
import br.com.desafiogrupoallcross.config.exception.http_400.CampoNuloProibidoException;
import br.com.desafiogrupoallcross.config.exception.http_400.CampoVazioProibidoException;
import br.com.desafiogrupoallcross.config.exception.http_400.DadoComTamanhoMaximoInvalidoException;
import br.com.desafiogrupoallcross.utilitarios.FactoryObjectMotherAndBuilder;
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
@DisplayName("Unitário - Classe ProdutoBusiness")
class ProdutoBusinessUnitTest {

    private ProdutoCadastrarUseCase cadastrarUseCase;

    private ProdutoBusiness produtoBusiness;

    @BeforeEach
    void criarCenario() {
        produtoBusiness = FactoryObjectMotherAndBuilder.gerarProdutoBusiness();
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
            var nome = FactoryObjectMotherAndBuilder.faker.lorem().characters(101, 120);
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

    @Nested
    @DisplayName("Categoria")
    class Categoria {

        @Test
        @DisplayName("nulo")
        void dadaCategoriaNula_QuandoSetar_EntaoLancarException() {
            Executable acao = () -> produtoBusiness.setCategoria(null);
            Assertions.assertThrows(CampoNuloProibidoException.class, acao);
        }
    }

    @Nested
    @DisplayName("Métodos Padrão")
    class MetodosPadrao {

        private ProdutoBusiness primeiroProduto;

        private ProdutoBusiness segundoProduto;

        @BeforeEach
        void criarCenarioParaMetodosPadrao() {
            primeiroProduto = FactoryObjectMotherAndBuilder.gerarProdutoBusiness();
            primeiroProduto.setId(1L);

            segundoProduto = FactoryObjectMotherAndBuilder.gerarProdutoBusiness();
            segundoProduto.setId(2L);
        }

        @Test
        @DisplayName("equals")
        void dadoDoisProdutosValidos_QuandoEquals_EntaoRetornarNotEquals() {
            Assertions.assertNotEquals(primeiroProduto, segundoProduto);
        }

        @Test
        @DisplayName("hashCode")
        void dadoDoisHashCodeValidos_QuandoHashCode_EntaoRetornarNotEquals() {
            var primeiroHash = primeiroProduto.hashCode();
            var segundoHash = segundoProduto.hashCode();
            Assertions.assertNotEquals(primeiroHash, segundoHash);
        }
    }
}

