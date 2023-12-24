package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.config.exception.http_400.FalhaAoSalvarProdutoException;
import br.com.desafiogrupoallcross.utilitarios.FabricaDeObjetosDeTeste;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.temporal.ChronoUnit;

@SpringBootTest
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@DisplayName("Unitário - Produto Adapter - Salvar")
class ProdutoSalvarAdapterUnitTest {

    @MockBean
    private ProdutoRepository repository;

    @Autowired
    private ProdutoSalvarAdapter salvarAdapter;

    @Nested
    @DisplayName("Dados válidos")
    class DadosValidos {

        @Test
        @DisplayName("completos")
        void dadoProdutoComDadosCompletos_QuandoSalvar_EntaoRetornarProdutoSalvo() {
            var produtoBusiness = FabricaDeObjetosDeTeste.gerarProdutoBusiness();

            var produtoEntity = ProdutoEntity.builder()
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
    }
}

