package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.CategoriaEntity;
import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.CategoriaRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.ProdutoSalvarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoSalvarAdapterException;
import br.com.desafiogrupoallcross.config.exception.http_404.CategoriaNaoEncontradaException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProdutoSalvarAdapter implements ProdutoSalvarOutputPort {

    private final ProdutoRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;

    @Transactional
    @Override
    public ProdutoBusiness salvar(ProdutoBusiness produtoBusiness) {

        return Optional.ofNullable(produtoBusiness)
                .map(ProdutoEntity::converterParaEntity)
                .map(this::linkarProdutoComCategoria)
                .map(this.produtoRepository::save)
                .map(ProdutoEntity::converterParaBusiness)
                .orElseThrow(ProdutoSalvarAdapterException::new);
    }

    private ProdutoEntity linkarProdutoComCategoria(ProdutoEntity produto) {
        var categoriaId = produto.getCategoria().getId();
        var categoria = this.buscarCategoria(categoriaId);
        produto.setCategoria(categoria);
        return produto;
    }

    private CategoriaEntity buscarCategoria(final Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));
    }
}

