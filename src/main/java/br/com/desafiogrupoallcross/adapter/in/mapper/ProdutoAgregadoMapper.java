package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoAgregadoDtoOut;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoAgregado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoAgregadoMapper {

    ProdutoAgregadoDtoOut toProdutoAgregadoDtoOut(ProdutoAgregado produtoAgregado);
}

