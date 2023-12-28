package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.port.out.AuditoriaProdutoOutputPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuditoriaProdutoAdapter implements AuditoriaProdutoOutputPort {

    private static final Logger log = LoggerFactory.getLogger(AuditoriaProdutoAdapter.class);

    private final ProdutoRepository produtoRepository;

    @Override
    public List<String> consultarAuditoriaDeProdutoPorId(Long id) {

        log.info("Iniciado adaptador para consultar auditoria de Produto com Id: {}.", id);

        var resposta = this.produtoRepository.findRevisions(id)
                .stream()
                .map(Object::toString)
                .toList();

        log.info("Finalizado adaptador para consultar auditoria de Produto com Id: {}.", id);

        return resposta;
    }
}

