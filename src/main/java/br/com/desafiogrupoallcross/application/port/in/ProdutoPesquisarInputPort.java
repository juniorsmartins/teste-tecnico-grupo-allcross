package br.com.desafiogrupoallcross.application.port.in;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoPesquisarInputPort {

    Page<ProdutoBusiness> pesquisar(ProdutoFiltro produtoFiltro, Pageable paginacao);
}

