package br.com.desafiogrupoallcross.adapter.out;

import br.com.desafiogrupoallcross.adapter.out.repository.ProdutoRepository;
import br.com.desafiogrupoallcross.application.port.out.ProdutoDeletarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_404.ProdutoNaoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProdutoDeletarAdapter implements ProdutoDeletarOutputPort {

    private final ProdutoRepository repository;

    @Transactional
    @Override
    public void deletarPorId(final Long id) {

        this.repository.findById(id)
            .ifPresentOrElse(this.repository::delete,
                () -> {throw new ProdutoNaoEncontradoException(id);}
            );
    }
}

