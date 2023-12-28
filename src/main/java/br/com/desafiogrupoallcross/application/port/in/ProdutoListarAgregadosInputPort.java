package br.com.desafiogrupoallcross.application.port.in;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoAgregado;

import java.util.List;

public interface ProdutoListarAgregadosInputPort {

    List<ProdutoAgregado> listarAgregados();
}

