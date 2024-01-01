package br.com.desafiogrupoallcross.application.port.in;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;

public interface FotoProdutoArmazenarInputPort {

    void armazenar(Long id, FotoProduto fotoProduto);
}

