package br.com.desafiogrupoallcross.application.port.in;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;

import java.util.List;

public interface FotoProdutoListarInputPort {

    List<FotoProduto> listar();
}

