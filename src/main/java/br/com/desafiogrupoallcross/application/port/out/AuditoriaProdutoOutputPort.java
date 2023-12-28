package br.com.desafiogrupoallcross.application.port.out;

import java.util.List;

public interface AuditoriaProdutoOutputPort {

    List<String> consultarAuditoriaDeProdutoPorId(Long id);
}

