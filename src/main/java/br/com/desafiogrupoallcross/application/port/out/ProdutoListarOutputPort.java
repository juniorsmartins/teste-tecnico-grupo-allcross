package br.com.desafiogrupoallcross.application.port.out;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;

import java.util.List;

public interface ProdutoListarOutputPort {

    List<ProdutoBusiness> listar();
}

