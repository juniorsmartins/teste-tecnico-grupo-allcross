package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.adapter.out.ProdutoSalvarAdapter;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoCadastrarUseCaseException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unitário - Produto UseCase - Cadastrar")
class ProdutoCadastrarUseCaseUnitTest {

    @MockBean
    private ProdutoSalvarAdapter salvarAdapter;

    @Autowired
    private ProdutoCadastrarUseCase cadastrarUseCase;

    @Nested
    @DisplayName("Exceções")
    class ProdutoException {

        @Test
        @DisplayName("com produto nulo")
        void dadoProdutoNulo_QuandoCadastrar_EntaoLancarException() {
            Executable acao = () -> cadastrarUseCase.cadastrar(null);
            Assertions.assertThrows(ProdutoCadastrarUseCaseException.class, acao);
            Mockito.verifyNoInteractions(salvarAdapter);
        }
    }
}

