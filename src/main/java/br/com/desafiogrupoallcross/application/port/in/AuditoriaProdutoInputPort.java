package br.com.desafiogrupoallcross.application.port.in;

import java.util.List;

public interface AuditoriaProdutoInputPort {

    List<String> consultarAuditoriaDeProdutoPorId(Long id);
}

