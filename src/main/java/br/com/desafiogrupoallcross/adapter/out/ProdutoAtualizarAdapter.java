package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.CategoriaRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.ProdutoAtualizarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoAtualizarAdapterException;
import br.com.desafiogrupoallcross.config.exception.http_404.CategoriaNaoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProdutoAtualizarAdapter implements ProdutoAtualizarOutputPort {

    private static final Logger log = LoggerFactory.getLogger(ProdutoAtualizarAdapter.class);

    private final ProdutoRepository repository;

    private final CategoriaRepository categoriaRepository;

    @Transactional
    @Override
    public ProdutoBusiness atualizar(ProdutoBusiness produtoBusiness) {

        log.info("Iniciado adaptador para atualizar Produto com Id: {}.", produtoBusiness.getId());

        var resposta = this.repository.findById(produtoBusiness.getId())
                .map(entidade -> this.linkarProdutoComCategoria(entidade, produtoBusiness.getCategoria().getId()))
                .map(entidade -> this.atualizarInformacoes(entidade, produtoBusiness))
                .map(ProdutoEntity::converterParaBusiness)
                .orElseThrow(ProdutoAtualizarAdapterException::new);

        log.info("Finalizado adaptador para atualizar Produto com Id: {}.", resposta.getId());

        return resposta;
    }

    private ProdutoEntity linkarProdutoComCategoria(ProdutoEntity produtoEntity, Long id) {
        return this.categoriaRepository.findById(id)
            .map(categoria -> {
                produtoEntity.setCategoria(categoria);
                return produtoEntity;
            })
            .orElseThrow(() -> new CategoriaNaoEncontradaException(id));
    }

    private ProdutoEntity atualizarInformacoes(ProdutoEntity entity, ProdutoBusiness business) {
        BeanUtils.copyProperties(business, entity, "id", "sku", "categoria", "dataCadastro", "dataAtualizacao");
        return entity;
    }
}

