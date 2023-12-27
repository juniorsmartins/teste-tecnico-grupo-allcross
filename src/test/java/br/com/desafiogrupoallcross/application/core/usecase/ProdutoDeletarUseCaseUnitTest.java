package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.adapter.out.ProdutoDeletarAdapter;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoDeletarUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unitário - Produto Adapter - Deletar")
class ProdutoDeletarUseCaseUnitTest {

    @MockBean
    private ProdutoDeletarAdapter deletarAdapter;

    @Autowired
    private ProdutoDeletarUseCase deletarUseCase;

    @Test
    @DisplayName("por id nulo")
    void dadoProdutoNulo_QuandoSalvar_EntaoLancarException() {
        Executable acao = () -> this.deletarUseCase.deletarPorId(null);
        Assertions.assertThrows(ProdutoDeletarUseCaseException.class, acao);
    }
}

