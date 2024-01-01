package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.in.dto.response.FotoProdutoListarDtoOut;
import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FotoProdutoMapper {

    FotoProdutoListarDtoOut toFotoProdutoListarDtoOut(FotoProduto fotoProduto);
}

