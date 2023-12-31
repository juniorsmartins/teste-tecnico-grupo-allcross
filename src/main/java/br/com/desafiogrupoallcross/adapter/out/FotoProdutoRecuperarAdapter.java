package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.utilitario.FotoUtils;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoRecuperar;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoRecuperarOutputPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FotoProdutoRecuperarAdapter implements FotoProdutoRecuperarOutputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoRecuperarAdapter.class);

    private final FotoProdutoRepository repository;

    private final ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<FotoProdutoRecuperar> recuperarImagem(final Long produtoId) {

        log.info("");

        var resposta = this.repository.findByProdutoId(produtoId)
                .stream()
                .map(this::converterParaRecuperar)
                .toList();

        log.info("");

        return resposta;
    }

    private FotoProdutoRecuperar converterParaRecuperar(FotoProdutoEntity entity) {
        var fotoDescompactadaEmByte = FotoUtils.descompactarImagem(entity.getFoto());

        var fotoProduto = new FotoProdutoRecuperar();
        fotoProduto.setFoto(fotoDescompactadaEmByte);
        fotoProduto.setNome(fotoProduto.getNome());
        fotoProduto.setTipo(fotoProduto.getTipo());
        fotoProduto.setTamanho(fotoProduto.getTamanho());

        return fotoProduto;
    }
}

