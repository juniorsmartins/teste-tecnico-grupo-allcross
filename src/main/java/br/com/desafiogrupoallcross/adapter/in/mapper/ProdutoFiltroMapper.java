package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.in.dto.filtro.ProdutoDtoFiltro;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoFiltroMapper {

    ProdutoFiltro toProdutoFiltro(ProdutoDtoFiltro produtoDtoFiltro);
}

