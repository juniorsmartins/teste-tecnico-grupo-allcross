package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoConsultarPorIdInputPort;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoConsultarPorIdOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class FotoProdutoConsultarPorIdUseCase implements FotoProdutoConsultarPorIdInputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoConsultarPorIdUseCase.class);

    private final FotoProdutoConsultarPorIdOutputPort fotoProdutoConsultarPorIdOutputPort;

    public FotoProdutoConsultarPorIdUseCase(FotoProdutoConsultarPorIdOutputPort fotoProdutoConsultarPorIdOutputPort) {
        this.fotoProdutoConsultarPorIdOutputPort = fotoProdutoConsultarPorIdOutputPort;
    }

    @Override
    public FotoProduto consultarPorId(final Long id) {

        log.info("Iniciado serviço para consultar FotoProduto por Id: {}", id);

        Objects.requireNonNull(id);

        var resposta = this.fotoProdutoConsultarPorIdOutputPort.consultarPorId(id);

        log.info("Finalizado serviço para consultar FotoProduto por Id: {}", id);

        return resposta;
    }
}

