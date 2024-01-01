package br.com.desafiogrupoallcross.application.port.out;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;

public interface FotoProdutoConsultarPorIdOutputPort {

    FotoProduto consultarPorId(Long id);
}

