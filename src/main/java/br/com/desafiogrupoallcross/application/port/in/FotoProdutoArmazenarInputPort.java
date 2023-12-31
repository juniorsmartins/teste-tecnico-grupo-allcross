package br.com.desafiogrupoallcross.application.port.in;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoRecuperar;

public interface FotoProdutoArmazenarInputPort {

    void armazenar(Long id, FotoProdutoRecuperar fotoProduto);
}

