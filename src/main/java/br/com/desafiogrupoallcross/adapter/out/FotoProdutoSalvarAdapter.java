package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoSalvarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_404.MultipartFileNaoEncontradoException;
import br.com.desafiogrupoallcross.config.exception.http_404.ProdutoNaoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FotoProdutoSalvarAdapter implements FotoProdutoSalvarOutputPort {

    private final FotoProdutoRepository fotoProdutoRepository;

    private final ProdutoRepository produtoRepository;

    @Transactional
    @Override
    public void salvar(final Long id, FotoProdutoBusiness fotoProdutoBusiness) {

        Optional.of(fotoProdutoBusiness)
                .map(this::converterParaEntity)
                .map(foto -> this.linkarFotoComProduto(id, foto))
                .map(this.fotoProdutoRepository::save);
    }

    private FotoProdutoEntity converterParaEntity(FotoProdutoBusiness fotoProdutoBusiness) {
        var fotoConvertida = this.converterMultipartFileParaArrayDeByte(fotoProdutoBusiness.getFoto());

        var fotoProdutoEntity = new FotoProdutoEntity();
        fotoProdutoEntity.setFoto(fotoConvertida);
        fotoProdutoEntity.setNome(fotoProdutoBusiness.getNome());
        fotoProdutoEntity.setDescricao(fotoProdutoBusiness.getDescricao());
        fotoProdutoEntity.setTipo(fotoProdutoBusiness.getTipo());
        fotoProdutoEntity.setTamanho(fotoProdutoBusiness.getTamanho());

        return fotoProdutoEntity;
    }

    private byte[] converterMultipartFileParaArrayDeByte(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new MultipartFileNaoEncontradoException();
        }
    }

    private FotoProdutoEntity linkarFotoComProduto(final Long id, FotoProdutoEntity fotoProdutoEntity) {
        var produtoEntity = this.buscarProduto(id);
        fotoProdutoEntity.setProduto(produtoEntity);
        return fotoProdutoEntity;
    }

    private ProdutoEntity buscarProduto(final Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }
}

