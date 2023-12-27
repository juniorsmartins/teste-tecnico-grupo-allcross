package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.config.exception.http_404.CategoriaNaoEncontradaException;
import br.com.desafiogrupoallcross.config.exception.http_404.ProdutoNaoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = "/sql/produtos/produtos-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/produtos/produtos-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DisplayName("Integração - Produto Adapter - Deletar")
class ProdutoDeletarAdapterIntegrationTest {

    @Autowired
    private ProdutoDeletarAdapter deletarAdapter;

    @Test
    @DisplayName("por id inexistente")
    void dadoProdutoComIdInexistente_QuandoDeletar_EntaoLancarException() {
        var produtoIdInexistente = 0L;
        Executable acao = () -> this.deletarAdapter.deletarPorId(produtoIdInexistente);
        Assertions.assertThrows(ProdutoNaoEncontradoException.class, acao);
    }
}

