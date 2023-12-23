package br.com.desafiogrupoallcross.adapter.out.mapper;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoOutMapper {

    ProdutoEntity toProdutoEntity(ProdutoBusiness produtoBusiness);

    ProdutoBusiness toProdutoBusiness(ProdutoEntity produtoEntity);
}

