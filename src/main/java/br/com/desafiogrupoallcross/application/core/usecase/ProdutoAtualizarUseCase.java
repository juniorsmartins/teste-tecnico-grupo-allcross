package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.ProdutoAtualizarInputPort;
import br.com.desafiogrupoallcross.application.port.out.ProdutoAtualizarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoAtualizarUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ProdutoAtualizarUseCase implements ProdutoAtualizarInputPort {

    private static final Logger log = LoggerFactory.getLogger(ProdutoAtualizarUseCase.class);

    private final ProdutoAtualizarOutputPort atualizarOutputPort;

    public ProdutoAtualizarUseCase(ProdutoAtualizarOutputPort atualizarOutputPort) {
        this.atualizarOutputPort = atualizarOutputPort;
    }

    @Override
    public ProdutoBusiness atualizar(ProdutoBusiness produtoBusiness) {

        log.info("Iniciado serviço para atualizar Produto com Id: {}.", produtoBusiness.getId());

        var resposta = Optional.of(produtoBusiness)
                .map(this.atualizarOutputPort::atualizar)
                .orElseThrow(ProdutoAtualizarUseCaseException::new);

        log.info("Finalizado serviço para atualizar Produto com Id: {}.", resposta.getId());

        return resposta;
    }
}

