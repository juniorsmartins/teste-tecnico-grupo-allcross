package br.com.desafiogrupoallcross.adapter.in.dto.request;

import com.github.javafaker.Faker;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Record ProdutoAtualizarDtoIn")
class ProdutoAtualizarDtoInUnitTest {

    public static Faker faker = new Faker();

    @Autowired
    private Validator validator;

    private ProdutoAtualizarDtoIn.ProdutoAtualizarDtoInBuilder atualizarDtoIn;

    @BeforeEach
    void criarCenario() {
        atualizarDtoIn = ProdutoAtualizarDtoIn.builder()
                .id(1L)
                .nome("Rádio")
                .ativo(true)
                .valorCusto(BigDecimal.valueOf(500))
                .icms(30D)
                .valorVenda(BigDecimal.valueOf(650))
                .quantidadeEstoque(1)
                .categoria(new CategoriaId(1L));
    }

    @Nested
    @DisplayName("Id")
    class TesteId {

        @Test
        @DisplayName("nulo")
        void dadoIdNulo_QuandoValidar_EntaoLancarException() {
            var paraValidar = atualizarDtoIn.id(null).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(longs = {-1, 0})
        @DisplayName("negativo e zerado")
        void dadoIdNegativoAndZerado_QuandoValidar_EntaoLancarException(Long id) {
            var paraValidar = atualizarDtoIn.id(id).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Nome")
    class TesteNome {

        @Test
        @DisplayName("nulo")
        void dadoNomeNulo_QuandoValidar_EntaoLancarException() {
            var paraValidar = atualizarDtoIn.nome(null).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   "})
        @DisplayName("vazio e em branco")
        void dadoNomeVazioAndEmBranco_QuandoValidar_EntaoLancarException(String nome) {
            var paraValidar = atualizarDtoIn.nome(nome).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @Test
        @DisplayName("tamanho inválido")
        void dadoChapeuComMaisCaracteresQuePermitido_QuandoValidar_EntaoLancarException() {
            var nomeTamanhoInvalido = faker.lorem().characters(101, 120);
            var paraValidar = atualizarDtoIn.nome(nomeTamanhoInvalido).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("ValorCusto")
    class TesteValorCusto {

        @Test
        @DisplayName("nulo")
        void dadoValorCustoNulo_QuandoValidar_EntaoLancarException() {
            var paraValidar = atualizarDtoIn.valorCusto(null).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(longs = {-100, 0})
        @DisplayName("negativo e zerado")
        void dadoValorCustoNegativoAndZerado_QuandoValidar_EntaoLancarException(Long valorCusto) {
            var paraValidar = atualizarDtoIn.valorCusto(BigDecimal.valueOf(valorCusto)).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("Icms")
    class TesteIcms {

        @Test
        @DisplayName("negativo")
        void dadoIcmsNegativo_QuandoValidar_EntaoLancarException() {
            var paraValidar = atualizarDtoIn.icms(-100).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("ValorVenda")
    class TesteValorVenda {

        @Test
        @DisplayName("nulo")
        void dadoValorVendaNulo_QuandoValidar_EntaoLancarException() {
            var paraValidar = atualizarDtoIn.valorVenda(null).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }

        @ParameterizedTest
        @ValueSource(longs = {-100, 0})
        @DisplayName("negativo e zerado")
        void dadoValorVendaNegativoAndZerado_QuandoValidar_EntaoLancarException(Long valorVenda) {
            var paraValidar = atualizarDtoIn.valorVenda(BigDecimal.valueOf(valorVenda)).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("QuantidadeEstoque")
    class TesteQuantidadeEstoque {

        @Test
        @DisplayName("negativo")
        void dadoQuantidadeEstoqueNegativo_QuandoValidar_EntaoLancarException() {
            var paraValidar = atualizarDtoIn.quantidadeEstoque(-100).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }

    @Nested
    @DisplayName("CategoriaId")
    class TesteCategoriaId {

        @Test
        @DisplayName("nulo")
        void dadoCategoriaIdNula_QuandoValidar_EntaoLancarException() {
            var paraValidar = atualizarDtoIn.categoria(null).build();
            Set<ConstraintViolation<ProdutoAtualizarDtoIn>> violations = validator.validate(paraValidar);
            Assertions.assertFalse(violations.isEmpty());
            Assertions.assertEquals(1, violations.size());
        }
    }
}

