package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBuscar;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoBuscarTodosInputPort;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoBuscarTodosOutputPort;

import java.util.List;
import java.util.Optional;

public class FotoProdutoBuscarTodosUseCase implements FotoProdutoBuscarTodosInputPort {

    private final FotoProdutoBuscarTodosOutputPort buscarTodosOutputPort;

    public FotoProdutoBuscarTodosUseCase(FotoProdutoBuscarTodosOutputPort buscarTodosOutputPort) {
        this.buscarTodosOutputPort = buscarTodosOutputPort;
    }

    @Override
    public List<FotoProdutoBuscar> buscarImagens() {

        return this.buscarTodosOutputPort.buscarImagens();
    }
}

