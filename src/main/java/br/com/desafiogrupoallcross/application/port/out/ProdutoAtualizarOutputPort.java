package br.com.desafiogrupoallcross.application.port.out;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;

public interface ProdutoAtualizarOutputPort {

    ProdutoBusiness atualizar(ProdutoBusiness produtoBusiness);
}

