package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.adapter.out.ProdutoAtualizarAdapter;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoAtualizarUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
@DisplayName("Unitário - Produto UseCase - Atualizar")
class ProdutoAtualizarUseCaseUnitTest {

    @MockBean
    private ProdutoAtualizarAdapter atualizarAdapter;

    @Autowired
    private ProdutoAtualizarUseCase atualizarUseCase;

    @Nested
    @DisplayName("Exceções")
    class ProdutoException {

        @Test
        @DisplayName("com produto nulo")
        void dadoProdutoNulo_QuandoAtualizar_EntaoLancarException() {
            Executable acao = () -> atualizarUseCase.atualizar(null);
            Assertions.assertThrows(ProdutoAtualizarUseCaseException.class, acao);
            Mockito.verifyNoInteractions(atualizarAdapter);
        }
    }
}

