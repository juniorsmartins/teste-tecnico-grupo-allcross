package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.in.dto.request.CategoriaId;
import br.com.desafiogrupoallcross.adapter.in.dto.response.CategoriaDtoOut;
import br.com.desafiogrupoallcross.application.core.domain.CategoriaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaBusiness toCategoriaBusiness(CategoriaId categoriaId);

    CategoriaDtoOut toCategoriaDtoOut(CategoriaBusiness categoriaBusiness);
}

