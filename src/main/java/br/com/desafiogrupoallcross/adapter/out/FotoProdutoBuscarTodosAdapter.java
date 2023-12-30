package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.repository.FotoProdutoRepository;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBuscar;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoBuscarTodosOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FotoProdutoBuscarTodosAdapter implements FotoProdutoBuscarTodosOutputPort {

    private final FotoProdutoRepository repository;

    @Override
    public List<FotoProdutoBuscar> buscarImagens() {

        return this.repository.findAll()
                .stream()
                .map(FotoProdutoEntity::converterEntityParaBusiness)
                .toList();
    }
}

