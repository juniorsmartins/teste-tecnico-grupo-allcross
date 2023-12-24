package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.ProdutoCadastrarInputPort;
import br.com.desafiogrupoallcross.application.port.out.ProdutoSalvarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoCadastrarUseCaseException;

import java.util.Optional;

public class ProdutoCadastrarUseCase implements ProdutoCadastrarInputPort {

    private final ProdutoSalvarOutputPort salvarOutputPort;

    public ProdutoCadastrarUseCase(ProdutoSalvarOutputPort salvarOutputPort) {
        this.salvarOutputPort = salvarOutputPort;
    }

    @Override
    public ProdutoBusiness cadastrar(ProdutoBusiness produtoBusiness) {

        return Optional.ofNullable(produtoBusiness)
                .map(salvarOutputPort::salvar)
                .orElseThrow(ProdutoCadastrarUseCaseException::new);
    }
}

