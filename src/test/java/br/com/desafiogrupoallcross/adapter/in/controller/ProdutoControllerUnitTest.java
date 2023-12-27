package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.application.port.in.ProdutoDeletarInputPort;
import br.com.desafiogrupoallcross.application.port.in.ProdutoInverterStatusAtivoInputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unitário - Produto Controller - Deletar")
class ProdutoControllerUnitTest {

    @MockBean
    private ProdutoDeletarInputPort deletarInputPort;

    @InjectMocks
    private ProdutoController controller;

    @Nested
    @DisplayName("Deletar")
    class DeletarException {

        @Test
        @DisplayName("por id nulo")
        void dadoIdDeProdutoNulo_QuandoDeletar_EntaoLancarException() {
            Long produtoId = null;
            Executable acao = () -> controller.deletePorId(produtoId);
            Assertions.assertThrows(NoSuchElementException.class, acao);
            Mockito.verifyNoInteractions(deletarInputPort);
        }
    }
}

