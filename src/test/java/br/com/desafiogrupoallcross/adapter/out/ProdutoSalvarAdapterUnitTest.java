package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.config.exception.http_400.FalhaAoSalvarProdutoException;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Random;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Produto Adapter - Salvar")
class ProdutoSalvarAdapterUnitTest {

    @MockBean
    private ProdutoRepository repository;

    @Autowired
    private ProdutoSalvarAdapter salvarAdapter;

    private ProdutoBusiness produtoBusiness;

    private ProdutoEntity produtoEntity;

    @BeforeEach
    void criadorDeCenarioDeTest() {
        produtoBusiness = FabricaDeObjetosDeTeste.gerarProdutoBusiness();

        produtoEntity = ProdutoEntity.builder()
                .id(FabricaDeObjetosDeTeste.random.nextLong(200) + 100)
                .nome(produtoBusiness.getNome())
                .ativo(produtoBusiness.isAtivo())
                .sku(produtoBusiness.getSku())
                .valorCusto(produtoBusiness.getValorCusto())
                .icms(produtoBusiness.getIcms())
                .valorVenda(produtoBusiness.getValorVenda())
                .dataCadastro(produtoBusiness.getDataCadastro())
                .quantidadeEstoque(produtoBusiness.getQuantidadeEstoque())
                .build();
    }

    @Nested
    @DisplayName("Dados válidos")
    class DadosValidos {

        @Test
        @DisplayName("completos")
        void dadoProdutoComDadosCompletos_QuandoSalvar_EntaoRetornarProdutoSalvo() {
            Mockito.when(repository.save(Mockito.any(ProdutoEntity.class))).thenReturn(produtoEntity);
            var resposta = salvarAdapter.salvar(produtoBusiness);

            Assertions.assertAll("Asserções Salvar",
                    () -> Assertions.assertNotNull(resposta.getId()),
                    () -> Assertions.assertEquals(produtoBusiness.getNome(), resposta.getNome()),
                    () -> Assertions.assertEquals(produtoBusiness.isAtivo(), resposta.isAtivo()),
                    () -> Assertions.assertEquals(produtoBusiness.getSku(), resposta.getSku()),
                    () -> Assertions.assertEquals(produtoBusiness.getValorCusto(), resposta.getValorCusto()),
                    () -> Assertions.assertEquals(produtoBusiness.getIcms(), resposta.getIcms()),
                    () -> Assertions.assertEquals(produtoBusiness.getValorVenda(), resposta.getValorVenda()),
                    () -> Assertions.assertEquals(produtoBusiness.getQuantidadeEstoque(), resposta.getQuantidadeEstoque()),
                    () -> Assertions.assertEquals(produtoEntity.getDataCadastro().truncatedTo(ChronoUnit.SECONDS),
                            resposta.getDataCadastro().truncatedTo(ChronoUnit.SECONDS))
                    );
        }
    }

    @Nested
    @DisplayName("Exceções")
    class ProdutoException {

        @Test
        @DisplayName("por produto nulo")
        void dadoProdutoNulo_QuandoSalvar_EntaoLancarException() {
            Executable acao = () -> salvarAdapter.salvar(null);
            Assertions.assertThrows(FalhaAoSalvarProdutoException.class, acao);
        }

        @Test
        @DisplayName("por campo nome nulo")
        void dadoProdutoComNomeNulo_QuandoSalvar_EntaoLancarException() {
            produtoBusiness.setNome(null);
            Executable acao = () -> salvarAdapter.salvar(produtoBusiness);
            Assertions.assertThrows(FalhaAoSalvarProdutoException.class, acao);
        }

        @Test
        @DisplayName("por campo valorCusto nulo")
        void dadoProdutoComValorCustoNulo_QuandoSalvar_EntaoLancarException() {
            produtoBusiness.setValorCusto(null);
            Executable acao = () -> salvarAdapter.salvar(produtoBusiness);
            Assertions.assertThrows(FalhaAoSalvarProdutoException.class, acao);
        }

        @Test
        @DisplayName("por campo valorVenda nulo")
        void dadoProdutoComValorVendaNulo_QuandoSalvar_EntaoLancarException() {
            produtoBusiness.setValorVenda(null);
            Executable acao = () -> salvarAdapter.salvar(produtoBusiness);
            Assertions.assertThrows(FalhaAoSalvarProdutoException.class, acao);
        }


    }
}

