package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoArmazenarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.FotoProdutoSalvarAdapterException;
import br.com.desafiogrupoallcross.config.exception.http_404.ProdutoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FotoProdutoArmazenarAdapter implements FotoProdutoArmazenarOutputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoArmazenarAdapter.class);

    private final FotoProdutoRepository repository;

    private final ProdutoRepository produtoRepository;

    @Transactional
    @Override
    public void armazenar(final Long id, final FotoProduto fotoProduto) {

        log.info("Iniciado adaptador para salvar imagem do Produto com Id: {}.", id);

        Objects.requireNonNull(id);
        Objects.requireNonNull(fotoProduto);

        Optional.of(fotoProduto)
            .map(this::converterParaEntity)
            .map(foto -> this.linkarFotoComProduto(id, foto))
            .map(this.repository::save)
            .orElseThrow(FotoProdutoSalvarAdapterException::new);

        log.info("Finalizado adaptador para salvar imagem do Produto com Id: {}.", id);
    }

    private FotoProdutoEntity converterParaEntity(FotoProduto fotoProduto) {

        return FotoProdutoEntity.builder()
            .nome(fotoProduto.getNome())
            .tipo(fotoProduto.getTipo())
            .tamanho(fotoProduto.getTamanho())
            .foto(fotoProduto.getFoto())
            .build();
    }

    private FotoProdutoEntity linkarFotoComProduto(final Long id, FotoProdutoEntity fotoEntity) {
        var produtoEntity = this.buscarProduto(id);
        fotoEntity.setProduto(produtoEntity);
        return fotoEntity;
    }

    private ProdutoEntity buscarProduto(final Long id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }
}

