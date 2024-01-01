package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.in.dto.request.FotoProdutoDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.response.FotoProdutoListarDtoOut;
import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FotoProdutoMapper {

    FotoProdutoBusiness toFotoProdutoBusiness(FotoProdutoDtoIn dtoIn);

    FotoProdutoListarDtoOut toFotoProdutoListarDtoOut(FotoProduto fotoProduto);
}

