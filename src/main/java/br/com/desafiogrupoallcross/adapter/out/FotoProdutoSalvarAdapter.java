package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoSalvarOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FotoProdutoSalvarAdapter implements FotoProdutoSalvarOutputPort {

    @Override
    public void cadastrarImagem(Long id, FotoProdutoBusiness fotoProdutoBusiness) {

        System.out.println("\n id: " + id);
    }
}

