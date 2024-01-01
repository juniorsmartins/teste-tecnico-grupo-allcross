package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoId;
import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoConsultarPorIdOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_404.FotoProdutoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class FotoProdutoConsultarPorIdAdapter implements FotoProdutoConsultarPorIdOutputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoConsultarPorIdAdapter.class);

    private final FotoProdutoRepository repository;

    private final ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    @Override
    public FotoProduto consultarPorId(final Long id) {

        log.info("Iniciado adaptador para consultar FotoProduto por Id: {}", id);

        var resposta = this.repository.findById(id)
                .map(this::converterEntityParaFotoProduto)
                .orElseThrow(() -> new FotoProdutoNaoEncontradoException(id));

        log.info("Finalizado adaptador para consultar FotoProduto por Id: {}", id);

        return resposta;
    }

    private FotoProduto converterEntityParaFotoProduto(FotoProdutoEntity entity) {

        var fotoProduto = new FotoProduto();
        fotoProduto.setId(entity.getId());
        fotoProduto.setFoto(entity.getFoto());
        fotoProduto.setNome(entity.getNome());
        fotoProduto.setTipo(entity.getTipo());
        fotoProduto.setTamanho(entity.getTamanho());
        fotoProduto.setProduto(new ProdutoId(entity.getId()));

        return fotoProduto;
    }
}

