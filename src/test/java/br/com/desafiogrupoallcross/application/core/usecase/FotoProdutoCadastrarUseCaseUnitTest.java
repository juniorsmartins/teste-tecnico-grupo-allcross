package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.adapter.out.FotoProdutoArmazenarAdapter;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/produtos/produtos-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/produtos/produtos-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayName("Unitário - FotoProduto UseCase - Cadastrar")
class FotoProdutoCadastrarUseCaseUnitTest {

    @MockBean
    private FotoProdutoArmazenarAdapter fotoProdutoArmazenarAdapter;

    @InjectMocks
    private FotoProdutoArmazenarUseCase fotoProdutoArmazenarUseCase;

    @Nested
    @DisplayName("Exceções")
    class FotoProdutoException {

        @Test
        @DisplayName("com fotoProduto nula")
        void dadoFotoProdutoNula_QuandoCadastrar_EntaoLancarException() {
            Executable acao = () -> fotoProdutoArmazenarUseCase.armazenar(2001L, null);
            Assertions.assertThrows(NullPointerException.class, acao);
            Mockito.verifyNoInteractions(fotoProdutoArmazenarAdapter);
        }
    }
}

