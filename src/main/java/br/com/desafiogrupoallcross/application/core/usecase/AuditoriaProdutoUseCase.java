package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.port.in.AuditoriaProdutoInputPort;
import br.com.desafiogrupoallcross.application.port.out.AuditoriaProdutoOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class AuditoriaProdutoUseCase implements AuditoriaProdutoInputPort {

    private static final Logger log = LoggerFactory.getLogger(AuditoriaProdutoUseCase.class);

    private final AuditoriaProdutoOutputPort auditoriaProdutoOutputPort;

    public AuditoriaProdutoUseCase(AuditoriaProdutoOutputPort auditoriaProdutoOutputPort) {
        this.auditoriaProdutoOutputPort = auditoriaProdutoOutputPort;
    }

    @Override
    public List<String> consultarAuditoriaDeProdutoPorId(final Long id) {

        log.info("Iniciado serviço para consultar auditoria de Produto com Id: {}.", id);

        var resposta = Optional.ofNullable(id)
                .map(this.auditoriaProdutoOutputPort::consultarAuditoriaDeProdutoPorId)
                .orElseThrow();

        log.info("Finalizado serviço para consultar auditoria de Produto com Id: {}.", id);

        return resposta;
    }
}

