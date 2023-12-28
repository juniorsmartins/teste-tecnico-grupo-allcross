package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.port.out.AuditoriaProdutoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuditoriaProdutoAdapter implements AuditoriaProdutoOutputPort {

    private final ProdutoRepository produtoRepository;

    @Override
    public List<String> consultarAuditoriaDeProdutoPorId(Long id) {

        return this.produtoRepository.findRevisions(id)
                .stream()
                .map(Object::toString)
                .toList();
    }
}

