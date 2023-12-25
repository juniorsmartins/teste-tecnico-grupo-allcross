package br.com.desafiogrupoallcross.application.port.out;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoPesquisarOutputPort {

    Page<ProdutoBusiness> pesquisar(ProdutoFiltro produtoFiltro, Pageable paginacao);
}

