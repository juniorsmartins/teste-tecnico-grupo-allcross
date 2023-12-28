package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.ProdutoListarOutputPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProdutoListarAdapter implements ProdutoListarOutputPort {

    private static final Logger log = LoggerFactory.getLogger(ProdutoListarAdapter.class);

    private final ProdutoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<ProdutoBusiness> listar() {

        log.info("Iniciado adaptador para listar Produtos.");

        var resposta = this.repository.findAll()
                .stream()
                .map(ProdutoEntity::converterParaBusiness)
                .toList();

        log.info("Finalizado adaptador para listar Produtos.");

        return resposta;
    }
}

