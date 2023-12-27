package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.config.exception.http_404.CategoriaNaoEncontradaException;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Integração - Produto Adapter - Salvar")
class ProdutoSalvarAdapterIntegrationTest {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoSalvarAdapter salvarAdapter;

    private ProdutoBusiness produtoBusiness;

    @BeforeEach
    void criarCenario() {
        produtoBusiness = FabricaDeObjetosDeTeste.gerarProdutoBusiness();
    }

    @Nested
    @DisplayName("Dados válidos")
    class DadosValidos {

        @Test
        @DisplayName("completos")
        void dadoProdutoComDadosCompletos_QuandoSalvar_EntaoRetornarProdutoSalvoComValoresIguaisAhEntrada() {
            var resposta = salvarAdapter.salvar(produtoBusiness);

            Assertions.assertAll("Asserções Salvar",
                    () -> Assertions.assertNotNull(resposta.getId()),
                    () -> Assertions.assertNotNull(resposta.getSku()),
                    () -> Assertions.assertEquals(produtoBusiness.getNome(), resposta.getNome()),
                    () -> Assertions.assertEquals(produtoBusiness.isAtivo(), resposta.isAtivo()),
                    () -> Assertions.assertEquals(produtoBusiness.getValorCusto(), resposta.getValorCusto()),
                    () -> Assertions.assertEquals(produtoBusiness.getIcms(), resposta.getIcms()),
                    () -> Assertions.assertEquals(produtoBusiness.getValorVenda(), resposta.getValorVenda()),
                    () -> Assertions.assertEquals(produtoBusiness.getQuantidadeEstoque(), resposta.getQuantidadeEstoque()),
                    () -> Assertions.assertEquals(produtoBusiness.getCategoria().getId(), resposta.getCategoria().getId()),
                    () -> Assertions.assertNotNull(resposta.getDataCadastro())
            );
        }

        @Test
        @DisplayName("persistidos")
        void dadoProdutoComDadosCompletos_QuandoSalvar_EntaoRetornarProdutoPersistido() {
            var resposta = salvarAdapter.salvar(produtoBusiness);
            var produtoPersistido = repository.findById(resposta.getId());
            Assertions.assertNotNull(produtoPersistido);
        }
    }

    @Nested
    @DisplayName("Exceções")
    class ProdutoException {
        @Test
        @DisplayName("por categoria inexistente")
        void dadoProdutoComCategoriaInexistente_QuandoSalvar_EntaoLancarException() {
            produtoBusiness.getCategoria().setId(0L);
            Executable acao = () -> salvarAdapter.salvar(produtoBusiness);
            Assertions.assertThrows(CategoriaNaoEncontradaException.class, acao);
        }
    }
}

