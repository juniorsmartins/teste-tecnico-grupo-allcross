package br.com.desafiogrupoallcross.application.port.in;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;

public interface FotoProdutoConsultarPorIdInputPort {

    FotoProduto consultarPorId(Long produtoId);
}


