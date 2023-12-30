package br.com.desafiogrupoallcross.application.port.out;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBuscar;

import java.util.List;

public interface FotoProdutoBuscarTodosOutputPort {

    List<FotoProdutoBuscar> buscarImagens();
}

