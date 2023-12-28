package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.ProdutoInverterStatusAtivoInputPort;
import br.com.desafiogrupoallcross.application.port.out.ProdutoInverterStatusAtivoOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoInverterStatusAtivoUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ProdutoInverterStatusAtivoUseCase implements ProdutoInverterStatusAtivoInputPort {

    private static final Logger log = LoggerFactory.getLogger(ProdutoInverterStatusAtivoUseCase.class);

    private final ProdutoInverterStatusAtivoOutputPort ativoOutputPort;

    public ProdutoInverterStatusAtivoUseCase(ProdutoInverterStatusAtivoOutputPort ativoOutputPort) {
        this.ativoOutputPort = ativoOutputPort;
    }

    @Override
    public ProdutoBusiness inverterStatusAtivo(final Long id) {

        log.info("Iniciado serviço para inverter status ativo do Produto com Id: {}.", id);

        var resposta = Optional.ofNullable(id)
                .map(this.ativoOutputPort::inverterStatusAtivo)
                .orElseThrow(ProdutoInverterStatusAtivoUseCaseException::new);

        log.info("Finalizado serviço para inverter status ativo do Produto com Id: {}.", resposta.getId());

        return resposta;
    }
}

