package br.com.desafiogrupoallcross.application.port.out;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;

public interface FotoProdutoArmazenarOutputPort {

    void armazenar(Long id, FotoProduto fotoProduto);
}

