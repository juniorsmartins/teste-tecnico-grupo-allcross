package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.ProdutoListarOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProdutoListarAdapter implements ProdutoListarOutputPort {

    private final ProdutoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<ProdutoBusiness> listar() {

        return this.repository.findAll()
                .stream()
                .map(ProdutoEntity::converterParaBusiness)
                .toList();
    }
}

