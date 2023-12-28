package br.com.desafiogrupoallcross.adapter.in.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unitário - Classe FotoProdutoDtoIn")
class FotoProdutoDtoInUnitTest {

    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private FotoProdutoDtoIn fotoProdutoDtoIn;

    @Test
    void testFotoProdutoDtoInBuilder() throws IOException {
        // Criar MockMultipartFile para usar nos testes
        MockMultipartFile mockFile = new MockMultipartFile("foto", "test.jpg", "image/jpeg", "test data".getBytes());

        // Construtor do builder
        fotoProdutoDtoIn = FotoProdutoDtoIn.builder()
                .foto(mockFile)
                .descricao("Teste descrição")
                .build();

        // Asserções para verificar se os valores foram configurados corretamente
        assertAll(
                () -> assertEquals(mockFile, fotoProdutoDtoIn.foto()),
                () -> assertEquals("Teste descrição", fotoProdutoDtoIn.descricao())
        );
    }
}