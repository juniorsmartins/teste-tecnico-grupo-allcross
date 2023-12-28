package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.ProdutoInverterStatusAtivoOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_404.ProdutoNaoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProdutoInverterStatusAtivoAdapter implements ProdutoInverterStatusAtivoOutputPort {

    private static final Logger log = LoggerFactory.getLogger(ProdutoInverterStatusAtivoAdapter.class);

    private final ProdutoRepository repository;

    @Transactional
    @Override
    public ProdutoBusiness inverterStatusAtivo(final Long id) {

        log.info("Iniciado adaptador para inverter status ativo do Produto com Id: {}.", id);

        var resposta = this.repository.findById(id)
                .map(this::inverterStatusAtivo)
                .map(ProdutoEntity::converterParaBusiness)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        log.info("Finalizado adaptador para inverter status ativo do Produto com Id: {}.", id);

        return resposta;
    }

    private ProdutoEntity inverterStatusAtivo(ProdutoEntity entity) {
        entity.setAtivo(!entity.isAtivo());
        return entity;
    }
}

