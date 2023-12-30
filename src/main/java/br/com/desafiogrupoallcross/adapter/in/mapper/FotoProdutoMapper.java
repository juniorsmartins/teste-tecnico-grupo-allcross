package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.in.dto.request.FotoProdutoDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.response.FotoProdutoDtoOut;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBuscar;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FotoProdutoMapper {

    FotoProdutoBusiness toFotoProdutoBusiness(FotoProdutoDtoIn dtoIn);

    FotoProdutoDtoOut toFotoProdutoDtoOut(FotoProdutoBuscar fotoProdutoBuscar);
}

