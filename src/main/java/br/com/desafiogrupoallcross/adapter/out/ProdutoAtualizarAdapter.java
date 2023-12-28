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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProdutoAtualizarAdapter implements ProdutoAtualizarOutputPort {

    private final ProdutoRepository repository;

    private final CategoriaRepository categoriaRepository;

    @Transactional
    @Override
    public ProdutoBusiness atualizar(ProdutoBusiness produtoBusiness) {

        return this.repository.findById(produtoBusiness.getId())
                .map(entidade -> this.linkarProdutoComCategoria(entidade, produtoBusiness.getCategoria().getId()))
                .map(entidade -> this.atualizarInformacoes(entidade, produtoBusiness))
                .map(ProdutoEntity::converterParaBusiness)
                .orElseThrow(ProdutoAtualizarAdapterException::new);
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

