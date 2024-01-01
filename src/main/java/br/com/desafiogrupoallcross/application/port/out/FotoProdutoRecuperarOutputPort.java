package br.com.desafiogrupoallcross.application.port.out;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;

import java.util.List;

public interface FotoProdutoRecuperarOutputPort {

    List<FotoProduto> recuperarImagem(Long produtoId);
}

