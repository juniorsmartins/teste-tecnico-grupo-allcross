package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.ProdutoAtualizarInputPort;
import br.com.desafiogrupoallcross.application.port.out.ProdutoAtualizarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoAtualizarUseCaseException;

import java.util.Optional;

public class ProdutoAtualizarUseCase implements ProdutoAtualizarInputPort {

    private final ProdutoAtualizarOutputPort atualizarOutputPort;

    public ProdutoAtualizarUseCase(ProdutoAtualizarOutputPort atualizarOutputPort) {
        this.atualizarOutputPort = atualizarOutputPort;
    }

    @Override
    public ProdutoBusiness atualizar(ProdutoBusiness produtoBusiness) {

        return Optional.ofNullable(produtoBusiness)
                .map(this.atualizarOutputPort::atualizar)
                .orElseThrow(ProdutoAtualizarUseCaseException::new);
    }
}

