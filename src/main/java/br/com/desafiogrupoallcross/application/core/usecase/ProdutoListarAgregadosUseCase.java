package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoAgregado;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.ProdutoListarAgregadosInputPort;
import br.com.desafiogrupoallcross.application.port.out.ProdutoListarOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoListarAgregadosUseCase implements ProdutoListarAgregadosInputPort {

    private static final Logger log = LoggerFactory.getLogger(ProdutoListarAgregadosUseCase.class);

    private final ProdutoListarOutputPort listarOutputPort;

    public ProdutoListarAgregadosUseCase(ProdutoListarOutputPort listarOutputPort) {
        this.listarOutputPort = listarOutputPort;
    }

    @Override
    public List<ProdutoAgregado> listarAgregados() {

        log.info("Iniciado serviço para listar Agregados de Produto.");

        var resposta = this.listarOutputPort.listar()
                .stream()
                .map(this::criarProdutoAgregado)
                .toList();

        log.info("Finalizado serviço para listar Agregados de Produto.");

        return resposta;
    }

    private ProdutoAgregado criarProdutoAgregado(ProdutoBusiness business) {

        var produtoAgregado = new ProdutoAgregado();
        produtoAgregado.setNome(business.getNome());
        produtoAgregado.setValorCusto(business.getValorCusto());
        produtoAgregado.setIcms(business.getIcms());
        produtoAgregado.setValorVenda(business.getValorVenda());
        produtoAgregado.setQuantidadeEstoque(business.getQuantidadeEstoque());

        var custoTotal = this.calcularCustoTotal(business.getValorCusto(), business.getQuantidadeEstoque());
        produtoAgregado.setCustoTotal(custoTotal);

        var valorTotal = this.calcularValorTotal(business.getValorVenda(), business.getQuantidadeEstoque());
        produtoAgregado.setValorTotal(valorTotal);

        return produtoAgregado;
    }

    private BigDecimal calcularCustoTotal(BigDecimal valorCusto, int quantidadeEstoque) {
        return valorCusto.multiply(BigDecimal.valueOf(quantidadeEstoque));
    }

    private BigDecimal calcularValorTotal(BigDecimal valorVenda, int quantidadeEstoque) {
        return valorVenda.multiply(BigDecimal.valueOf(quantidadeEstoque));
    }
}

