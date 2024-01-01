package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.in.dto.request.CategoriaId;
import br.com.desafiogrupoallcross.adapter.in.dto.response.CategoriaDtoOut;
import br.com.desafiogrupoallcross.application.core.domain.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria toCategoriaBusiness(CategoriaId categoriaId);

    CategoriaDtoOut toCategoriaDtoOut(Categoria categoria);
}

