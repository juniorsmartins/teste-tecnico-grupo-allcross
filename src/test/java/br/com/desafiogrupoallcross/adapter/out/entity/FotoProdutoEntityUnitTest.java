package br.com.desafiogrupoallcross.adapter.out.entity;

import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.utilitarios.FactoryObjectMotherAndBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unitário - Classe FotoProdutoEntity")
class FotoProdutoEntityUnitTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    private FotoProdutoEntity primeiraFoto;

    private FotoProdutoEntity segundaFoto;

    @BeforeEach
    void criarCenario() throws IOException {
        var produto = FactoryObjectMotherAndBuilder.gerarProdutoEntityBuilder().build();
        var produtoSalvo = produtoRepository.save(produto);

        primeiraFoto = FactoryObjectMotherAndBuilder.gerarFotoProdutoEntityBuilder()
                .id(1L)
                .produto(produtoSalvo)
                .build();

        segundaFoto = FactoryObjectMotherAndBuilder.gerarFotoProdutoEntityBuilder()
                .id(2L)
                .produto(produtoSalvo)
                .build();
    }

    @Nested
    @DisplayName("Métodos Padrão")
    class MetodosPadrao {

        @Test
        @DisplayName("equals")
        void dadoDoisProdutosValidos_QuandoEquals_EntaoRetornarNotEquals() {
            Assertions.assertNotEquals(primeiraFoto, segundaFoto);
        }

        @Test
        @DisplayName("hashCode")
        void dadoDoisHashCodeValidos_QuandoHashCode_EntaoRetornarNotEquals() {
            var primeiroHash = primeiraFoto.hashCode();
            var segundoHash = segundaFoto.hashCode();
            Assertions.assertNotEquals(primeiroHash, segundoHash);
        }
    }
}

