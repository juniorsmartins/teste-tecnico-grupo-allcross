package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import br.com.desafiogrupoallcross.application.port.in.ProdutoPesquisarInputPort;
import br.com.desafiogrupoallcross.application.port.out.ProdutoPesquisarOutputPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProdutoPesquisarUseCase implements ProdutoPesquisarInputPort {

    private final ProdutoPesquisarOutputPort pesquisarOutputPort;

    public ProdutoPesquisarUseCase(ProdutoPesquisarOutputPort produtoPesquisarOutputPort) {
        this.pesquisarOutputPort = produtoPesquisarOutputPort;
    }

    @Override
    public Page<ProdutoBusiness> pesquisar(final ProdutoFiltro produtoFiltro, final Pageable paginacao) {

        return this.pesquisarOutputPort.pesquisar(produtoFiltro, paginacao);
    }
}
