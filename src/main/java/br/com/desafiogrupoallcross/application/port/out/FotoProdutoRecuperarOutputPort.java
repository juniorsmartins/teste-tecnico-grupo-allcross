package br.com.desafiogrupoallcross.application.port.out;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoRecuperar;

import java.util.List;

public interface FotoProdutoRecuperarOutputPort {

    List<FotoProdutoRecuperar> recuperarImagem(Long produtoId);
}

