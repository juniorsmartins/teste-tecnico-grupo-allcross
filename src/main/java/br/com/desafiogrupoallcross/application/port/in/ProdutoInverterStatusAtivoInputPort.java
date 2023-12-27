package br.com.desafiogrupoallcross.application.port.in;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;

public interface ProdutoInverterStatusAtivoInputPort {

    ProdutoBusiness inverterStatusAtivo(Long id);
}

