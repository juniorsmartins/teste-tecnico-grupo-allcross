package br.com.desafiogrupoallcross.application.port.out;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoRecuperar;

public interface FotoProdutoArmazenarOutputPort {

    void armazenar(Long id, FotoProdutoRecuperar fotoProdutoRecuperar);
}

