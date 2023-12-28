package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoSalvarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.FotoProdutoSalvarAdapterException;
import br.com.desafiogrupoallcross.config.exception.http_404.MultipartFileNaoEncontradoException;
import br.com.desafiogrupoallcross.config.exception.http_404.ProdutoNaoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FotoProdutoSalvarAdapter implements FotoProdutoSalvarOutputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoSalvarAdapter.class);

    private final FotoProdutoRepository fotoProdutoRepository;

    private final ProdutoRepository produtoRepository;

    @Transactional
    @Override
    public void salvar(final Long id, FotoProdutoBusiness fotoProdutoBusiness) {

        log.info("Iniciado adaptador para salvar imagem do Produto com Id: {}.", id);

        Optional.ofNullable(fotoProdutoBusiness)
                .map(this::converterParaEntity)
                .map(foto -> this.linkarFotoComProduto(id, foto))
                .map(this.fotoProdutoRepository::save)
                .orElseThrow(FotoProdutoSalvarAdapterException::new);

        log.info("Finalizado adaptador para salvar imagem do Produto com Id: {}.", id);
    }

    private FotoProdutoEntity converterParaEntity(FotoProdutoBusiness fotoProdutoBusiness) {
        var fotoConvertida = this.converterMultipartFileParaArrayDeByte(fotoProdutoBusiness);

        var fotoProdutoEntity = new FotoProdutoEntity();
        fotoProdutoEntity.setFoto(fotoConvertida);
        fotoProdutoEntity.setNome(fotoProdutoBusiness.getNome());
        fotoProdutoEntity.setDescricao(fotoProdutoBusiness.getDescricao());
        fotoProdutoEntity.setTipo(fotoProdutoBusiness.getTipo());
        fotoProdutoEntity.setTamanho(fotoProdutoBusiness.getTamanho());

        return fotoProdutoEntity;
    }

    private byte[] converterMultipartFileParaArrayDeByte(FotoProdutoBusiness fotoProdutoBusiness) {
        return Optional.ofNullable(fotoProdutoBusiness.getFoto())
                .map(this::getMultipartFileBytes)
                .orElseThrow(MultipartFileNaoEncontradoException::new);
    }

    private byte[] getMultipartFileBytes(MultipartFile file) {
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

