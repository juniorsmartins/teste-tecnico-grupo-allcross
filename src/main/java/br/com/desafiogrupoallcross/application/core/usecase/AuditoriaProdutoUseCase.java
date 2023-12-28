package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.port.in.AuditoriaProdutoInputPort;
import br.com.desafiogrupoallcross.application.port.out.AuditoriaProdutoOutputPort;

import java.util.List;
import java.util.Optional;

public class AuditoriaProdutoUseCase implements AuditoriaProdutoInputPort {

    private final AuditoriaProdutoOutputPort auditoriaProdutoOutputPort;

    public AuditoriaProdutoUseCase(AuditoriaProdutoOutputPort auditoriaProdutoOutputPort) {
        this.auditoriaProdutoOutputPort = auditoriaProdutoOutputPort;
    }

    @Override
    public List<String> consultarAuditoriaDeProdutoPorId(final Long id) {

        return Optional.ofNullable(id)
                .map(this.auditoriaProdutoOutputPort::consultarAuditoriaDeProdutoPorId)
                .orElseThrow();
    }
}

