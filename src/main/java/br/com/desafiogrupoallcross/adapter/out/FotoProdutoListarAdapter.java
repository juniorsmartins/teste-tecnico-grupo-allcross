package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoId;
import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoListarOutputPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FotoProdutoListarAdapter implements FotoProdutoListarOutputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoListarAdapter.class);

    private final FotoProdutoRepository fotoProdutoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<FotoProduto> listar() {

        log.info("Iniciado adaptador para listar FotosProduto.");

        var resposta = this.fotoProdutoRepository.listar()
                .stream()
                .map(this::converterEntityParaFotoProdutoSemImagem)
                .toList();

        log.info("Finalizado adaptador para listar FotosProduto.");

        return resposta;
    }

    private FotoProduto converterEntityParaFotoProdutoSemImagem(FotoProdutoEntity entity) {

        var fotoProduto = new FotoProduto();
        fotoProduto.setId(entity.getId());
        fotoProduto.setNome(entity.getNome());
        fotoProduto.setTipo(entity.getTipo());
        fotoProduto.setTamanho(entity.getTamanho());
        fotoProduto.setProduto(new ProdutoId(entity.getProduto().getId()));

        return fotoProduto;
    }
}

