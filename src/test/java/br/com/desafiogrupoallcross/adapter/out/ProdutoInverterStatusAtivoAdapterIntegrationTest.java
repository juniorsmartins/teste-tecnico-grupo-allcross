package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/produtos/produtos-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/produtos/produtos-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Integração - Produto Adapter - Salvar")
class ProdutoInverterStatusAtivoAdapterIntegrationTest {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoInverterStatusAtivoAdapter ativoAdapter;

    private ProdutoEntity produtoAtivo;

    private ProdutoEntity produtoInativo;

    @BeforeEach
    void criarCenario() {
        produtoAtivo = this.repository.findById(2001L).get();
        produtoInativo = this.repository.findById(2002L).get();
    }

    @Test
    @DisplayName("false persistido")
    void dadoProdutoComStatusAtivoTrue_QuandoInverterStatusAtivo_EntaoPersistirComoFalse() {
        var idAtivo = produtoAtivo.getId();
        Assertions.assertTrue(produtoAtivo.isAtivo());

        var resposta = this.ativoAdapter.inverterStatusAtivo(idAtivo);
        Assertions.assertFalse(resposta.isAtivo());

        var produtoPersistido = repository.findById(idAtivo).get();
        Assertions.assertFalse(produtoPersistido.isAtivo());
    }

    @Test
    @DisplayName("true persistido")
    void dadoProdutoComStatusAtivoFalse_QuandoInverterStatusAtivo_EntaoPersistirComoTrue() {
        var idInativo = produtoInativo.getId();
        Assertions.assertFalse(produtoInativo.isAtivo());

        var resposta = this.ativoAdapter.inverterStatusAtivo(idInativo);
        Assertions.assertTrue(resposta.isAtivo());

        var produtoPersistido = repository.findById(idInativo).get();
        Assertions.assertTrue(produtoPersistido.isAtivo());
    }
}