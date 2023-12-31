package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.spec.ProdutoSpecification;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import br.com.desafiogrupoallcross.application.port.out.ProdutoPesquisarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoPesquisarAdapterException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProdutoPesquisarAdapter implements ProdutoPesquisarOutputPort {

    private static final Logger log = LoggerFactory.getLogger(ProdutoPesquisarAdapter.class);

    private final ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<ProdutoBusiness> pesquisar(final ProdutoFiltro produtoFiltro, final Pageable paginacao) {

        log.info("Iniciado adaptador para pesquisar Produtos.");

        var resposta = Optional.ofNullable(produtoFiltro)
                .map(parametros -> this.produtoRepository
                    .findAll(ProdutoSpecification.consultarDinamicamente(parametros), paginacao))
                .map(pagina -> pagina.map(ProdutoEntity::converterParaBusiness))
                .orElseThrow(ProdutoPesquisarAdapterException::new);

        log.info("Finalizado adaptador para pesquisar Produtos.");

        return resposta;
    }
}

