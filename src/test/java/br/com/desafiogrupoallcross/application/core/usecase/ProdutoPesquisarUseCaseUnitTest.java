package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.adapter.out.ProdutoSalvarAdapter;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoPesquisarUseCaseException;
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
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unitário - Produto UseCase - Pesquisar")
class ProdutoPesquisarUseCaseUnitTest {

    @MockBean
    private ProdutoSalvarAdapter salvarAdapter;

    @Autowired
    private ProdutoPesquisarUseCase pesquisarUseCase;

    @Nested
    @DisplayName("Exceções")
    class ProdutoPesquisarException {

        @Test
        @DisplayName("com produto nulo")
        void dadoProdutoNulo_QuandoPesquisar_EntaoLancarException() {
            Pageable paginacao = null;
            Executable acao = () -> pesquisarUseCase.pesquisar(null, paginacao);
            Assertions.assertThrows(ProdutoPesquisarUseCaseException.class, acao);
            Mockito.verifyNoInteractions(salvarAdapter);
        }
    }

}