package br.com.desafiogrupoallcross.application.port.in;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoRecuperar;

import java.util.List;

public interface FotoProdutoRecuperarInputPort {

    List<FotoProdutoRecuperar> recuperarImagem(Long produtoId);
}

