package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.ProdutoSalvarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.FalhaAoSalvarProdutoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProdutoSalvarAdapter implements ProdutoSalvarOutputPort {

    private final ProdutoRepository produtoRepository;

    @Override
    public ProdutoBusiness salvar(ProdutoBusiness produtoBusiness) {

        return Optional.ofNullable(produtoBusiness)
                .map(ProdutoEntity::converterParaEntity)
                .map(this.produtoRepository::save)
                .map(ProdutoEntity::converterParaBusiness)
                .orElseThrow(FalhaAoSalvarProdutoException::new);
    }
}

