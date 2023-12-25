package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.config.exception.http_400.FotoProdutoSalvarAdapterException;
import br.com.desafiogrupoallcross.config.exception.http_404.MultipartFileNaoEncontradoException;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unitário - FotoProduto Adapter - Salvar")
class FotoProdutoSalvarAdapterUnitTest {

    @MockBean
    private FotoProdutoRepository fotoRepository;

    @Autowired
    private FotoProdutoSalvarAdapter fotoSalvarAdapter;

    @Autowired
    private ProdutoRepository produtoRepository;

    private ProdutoEntity produtoSalvo;

    @BeforeEach
    void criarCenario() throws IOException {
        var produto = FabricaDeObjetosDeTeste.gerarProdutoEntityBuilder().build();
        produtoSalvo = produtoRepository.save(produto);
    }

    @Nested
    @DisplayName("Exceções")
    class FotoProdutoException {

        @Test
        @DisplayName("por foto nula")
        void dadoFotoProdutoNulo_QuandoSalvar_EntaoLancarException() {
            Executable acao = () -> fotoSalvarAdapter.salvar(produtoSalvo.getId(), null);
            Assertions.assertThrows(FotoProdutoSalvarAdapterException.class, acao);
        }

        @Test
        @DisplayName("por fotoProduto sem foto")
        void dadoFotoProdutoSemFoto_QuandoSalvar_EntaoLancarException() throws IOException {
            var fotoProdutoSemFoto = FabricaDeObjetosDeTeste.gerarFotoProdutoBusinessSemFoto();
            Executable acao = () -> fotoSalvarAdapter.salvar(produtoSalvo.getId(), fotoProdutoSemFoto);
            Assertions.assertThrows(MultipartFileNaoEncontradoException.class, acao);
        }
    }

}