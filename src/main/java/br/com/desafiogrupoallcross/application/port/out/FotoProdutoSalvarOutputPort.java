package br.com.desafiogrupoallcross.application.port.out;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;

public interface FotoProdutoSalvarOutputPort {

    void cadastrarImagem(Long id, FotoProdutoBusiness fotoProdutoBusiness);
}

