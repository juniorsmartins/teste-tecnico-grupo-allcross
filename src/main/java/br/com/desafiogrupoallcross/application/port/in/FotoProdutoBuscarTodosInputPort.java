package br.com.desafiogrupoallcross.application.port.in;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBuscar;

import java.util.List;

public interface FotoProdutoBuscarTodosInputPort {

    List<FotoProdutoBuscar> buscarImagens();
}

