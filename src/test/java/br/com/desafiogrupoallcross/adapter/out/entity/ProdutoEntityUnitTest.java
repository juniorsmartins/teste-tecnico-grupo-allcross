package br.com.desafiogrupoallcross.adapter.out.entity;

import br.com.desafiogrupoallcross.utilitarios.FactoryObjectMotherAndBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unitário - Classe ProdutoEntity")
class ProdutoEntityUnitTest {

    @Nested
    @DisplayName("Métodos Padrão")
    class MetodosPadrao {

        private ProdutoEntity primeiroProduto;

        private ProdutoEntity segundoProduto;

        @BeforeEach
        void criarCenarioParaMetodosPadrao() {
            primeiroProduto = FactoryObjectMotherAndBuilder.gerarProdutoEntityBuilder().build();
            primeiroProduto.setId(1L);

            segundoProduto = FactoryObjectMotherAndBuilder.gerarProdutoEntityBuilder().build();
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

