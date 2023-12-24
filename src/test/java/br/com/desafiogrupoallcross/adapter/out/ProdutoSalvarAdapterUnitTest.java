package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.config.exception.http_400.FalhaAoSalvarProdutoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
@DisplayName("Unitário - Produto Adapter - Salvar")
class ProdutoSalvarAdapterUnitTest {

    @MockBean
    private ProdutoRepository repository;

    @Autowired
    private ProdutoSalvarAdapter salvarAdapter;

    @Nested
    @DisplayName("Exceções")
    class ProdutoException {

        @Test
        @DisplayName("por produto nulo")
        void dadoProdutoNulo_QuandoSalvar_EntaoLancarException() {
            Executable acao = () -> salvarAdapter.salvar(null);
            Assertions.assertThrows(FalhaAoSalvarProdutoException.class, acao);
        }
    }
}

