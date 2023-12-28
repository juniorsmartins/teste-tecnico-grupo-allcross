package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.adapter.out.ProdutoInverterStatusAtivoAdapter;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoInverterStatusAtivoUseCaseException;
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
@DisplayName("Unitário - Produto UseCase - Inverter Status Ativo")
class ProdutoInverterStatusAtivoUseCaseUnitTest {

    @MockBean
    private ProdutoInverterStatusAtivoAdapter ativoAdapter;

    @Autowired
    private ProdutoInverterStatusAtivoUseCase ativoUseCase;

    @Nested
    @DisplayName("Exceções")
    class ProdutoInverterStatusAtivoException {

        @Test
        @DisplayName("com id de produto nulo")
        void dadoIdNuloDeProduto_QuandoInverterStatusAtivo_EntaoLancarException() {
            Executable acao = () -> ativoUseCase.inverterStatusAtivo(null);
            Assertions.assertThrows(ProdutoInverterStatusAtivoUseCaseException.class, acao);
            Mockito.verifyNoInteractions(ativoAdapter);
        }
    }
}

