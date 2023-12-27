package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.port.in.ProdutoDeletarInputPort;
import br.com.desafiogrupoallcross.application.port.out.ProdutoDeletarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoDeletarUseCaseException;

import java.util.Optional;

public class ProdutoDeletarUseCase implements ProdutoDeletarInputPort {

    private final ProdutoDeletarOutputPort deletarOutputPort;

    public ProdutoDeletarUseCase(ProdutoDeletarOutputPort deletarOutputPort) {
        this.deletarOutputPort = deletarOutputPort;
    }

    @Override
    public void deletarPorId(final Long id) {

        Optional.ofNullable(id)
            .ifPresentOrElse(this.deletarOutputPort::deletarPorId,
                () -> {throw new ProdutoDeletarUseCaseException();}
            );
    }
}

