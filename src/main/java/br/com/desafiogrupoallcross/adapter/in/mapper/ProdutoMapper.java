package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoCadastrarDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoCadastrarDtoOut;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoPesquisarDtoOut;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoBusiness toProdutoBusiness(ProdutoCadastrarDtoIn dtoIn);

    ProdutoCadastrarDtoOut toProdutoCadastrarDtoOut(ProdutoBusiness produtoBusiness);

    ProdutoPesquisarDtoOut toProdutoPesquisarDtoOut(ProdutoBusiness produtoBusiness);
}

