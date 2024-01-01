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

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FotoProdutoListarAdapter implements FotoProdutoListarOutputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoListarAdapter.class);

    private final FotoProdutoRepository repository;

    @Override
    public List<FotoProduto> listar() {

        log.info("");

        var resposta = this.repository.listar()
                .stream()
                .map(this::converterParaFotoProduto)
                .toList();

        log.info("");

        return resposta;
    }

    private FotoProduto converterParaFotoProduto(FotoProdutoEntity entity) {
        var fotoProduto = new FotoProduto();
        fotoProduto.setId(entity.getId());
        fotoProduto.setNome(entity.getNome());
        fotoProduto.setTipo(entity.getTipo());
        fotoProduto.setTamanho(entity.getTamanho());
        fotoProduto.setProduto(new ProdutoId(entity.getProduto().getId()));

        return fotoProduto;
    }
}

