package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.ProdutoInverterStatusAtivoOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_404.ProdutoNaoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProdutoInverterStatusAtivoAdapter implements ProdutoInverterStatusAtivoOutputPort {

    private final ProdutoRepository repository;

    @Transactional
    @Override
    public ProdutoBusiness inverterStatusAtivo(final Long id) {

        return this.repository.findById(id)
                .map(this::inverterStatusAtivo)
                .map(ProdutoEntity::converterParaBusiness)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    private ProdutoEntity inverterStatusAtivo(ProdutoEntity entity) {
        entity.setAtivo(!entity.isAtivo());
        return entity;
    }
}

