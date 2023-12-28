package br.com.desafiogrupoallcross.adapter.in.dto.request;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Record CategoriaId")
class CategoriaIdTest {

    @Autowired
    private Validator validator;

    @Test
    @DisplayName("com id nulo")
    void dadoIdNulo_QuandoValidar_EntaoLancarException() {
        var categoriaId = new CategoriaId(null);
        Set<ConstraintViolation<CategoriaId>> violations = validator.validate(categoriaId);
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
    }

    @ParameterizedTest
    @ValueSource(longs = {-1, 0})
    @DisplayName("com id negativo e zerado")
    void dadoIdNegativoAndZerado_QuandoValidar_EntaoLancarException(Long id) {
        var categoriaId = new CategoriaId(id);
        Set<ConstraintViolation<CategoriaId>> violations = validator.validate(categoriaId);
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(1, violations.size());
    }
}

