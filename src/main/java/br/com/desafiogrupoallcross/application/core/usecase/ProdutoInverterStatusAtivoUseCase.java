package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.ProdutoInverterStatusAtivoInputPort;
import br.com.desafiogrupoallcross.application.port.out.ProdutoInverterStatusAtivoOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoInverterStatusAtivoUseCaseException;

import java.util.Optional;

public class ProdutoInverterStatusAtivoUseCase implements ProdutoInverterStatusAtivoInputPort {

    private final ProdutoInverterStatusAtivoOutputPort ativoOutputPort;

    public ProdutoInverterStatusAtivoUseCase(ProdutoInverterStatusAtivoOutputPort ativoOutputPort) {
        this.ativoOutputPort = ativoOutputPort;
    }

    @Override
    public ProdutoBusiness inverterStatusAtivo(final Long id) {

        return Optional.ofNullable(id)
                .map(this.ativoOutputPort::inverterStatusAtivo)
                .orElseThrow(ProdutoInverterStatusAtivoUseCaseException::new);
    }
}

