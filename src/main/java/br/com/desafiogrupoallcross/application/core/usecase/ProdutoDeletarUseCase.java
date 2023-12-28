package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.port.in.ProdutoDeletarInputPort;
import br.com.desafiogrupoallcross.application.port.out.ProdutoDeletarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoDeletarUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ProdutoDeletarUseCase implements ProdutoDeletarInputPort {

    private static final Logger log = LoggerFactory.getLogger(ProdutoDeletarUseCase.class);

    private final ProdutoDeletarOutputPort deletarOutputPort;

    public ProdutoDeletarUseCase(ProdutoDeletarOutputPort deletarOutputPort) {
        this.deletarOutputPort = deletarOutputPort;
    }

    @Override
    public void deletarPorId(final Long id) {

        log.info("Iniciado serviço para deletar Produto com Id: {}.", id);

        Optional.ofNullable(id)
            .ifPresentOrElse(this.deletarOutputPort::deletarPorId,
                () -> {throw new ProdutoDeletarUseCaseException();}
            );

        log.info("Finalizado serviço para deletar Produto com id: {}.", id);
    }
}

