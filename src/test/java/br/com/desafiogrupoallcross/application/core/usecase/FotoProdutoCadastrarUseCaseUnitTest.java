package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.adapter.out.FotoProdutoArmazenarAdapter;
import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.config.exception.http_400.FotoProdutoCadastrarUseCaseException;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unitário - FotoProduto UseCase - Cadastrar")
class FotoProdutoCadastrarUseCaseUnitTest {

    @MockBean
    private FotoProdutoArmazenarAdapter fotoProdutoArmazenarAdapter;

    @Autowired
    private FotoProdutoArmazenarUseCase fotoProdutoArmazenarUseCase;

    @Autowired
    private ProdutoRepository produtoRepository;

    private ProdutoEntity produtoSalvo;

    @BeforeEach
    void criarCenario() throws IOException {
        var produto = FabricaDeObjetosDeTeste.gerarProdutoEntityBuilder().build();
        produtoSalvo = produtoRepository.save(produto);
    }

    @Nested
    @DisplayName("Exceções")
    class FotoProdutoException {

        @Test
        @DisplayName("com fotoProduto nula")
        void dadoFotoProdutoNula_QuandoCadastrar_EntaoLancarException() {
            Executable acao = () -> fotoProdutoArmazenarUseCase.armazenar(produtoSalvo.getId(), null);
            Assertions.assertThrows(FotoProdutoCadastrarUseCaseException.class, acao);
            Mockito.verifyNoInteractions(fotoProdutoArmazenarAdapter);
        }
    }
}

