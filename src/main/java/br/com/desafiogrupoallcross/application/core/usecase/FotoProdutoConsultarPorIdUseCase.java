package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoConsultarPorIdInputPort;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoConsultarPorIdOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

public class FotoProdutoConsultarPorIdUseCase implements FotoProdutoConsultarPorIdInputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoConsultarPorIdUseCase.class);

    private final FotoProdutoConsultarPorIdOutputPort recuperarOutputPort;

    public FotoProdutoConsultarPorIdUseCase(FotoProdutoConsultarPorIdOutputPort recuperarOutputPort) {
        this.recuperarOutputPort = recuperarOutputPort;
    }

    @Override
    public FotoProduto consultarPorId(final Long id) {

        log.info("");

        Objects.requireNonNull(id);

        var resposta = Optional.of(id)
                .map(this.recuperarOutputPort::consultarPorId)
                .orElseThrow();

        log.info("");

        return resposta;
    }
}

