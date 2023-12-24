package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoCadastrarInputPort;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoSalvarOutputPort;

public class FotoProdutoCadastrarUseCase implements FotoProdutoCadastrarInputPort {

    private final FotoProdutoSalvarOutputPort salvarOutputPort;

    public FotoProdutoCadastrarUseCase(FotoProdutoSalvarOutputPort salvarOutputPort) {
        this.salvarOutputPort = salvarOutputPort;
    }

    @Override
    public void cadastrarImagem(Long id, FotoProdutoBusiness fotoProdutoBusiness) {

        System.out.println("\n id: " + id);

        salvarOutputPort.cadastrarImagem(id, fotoProdutoBusiness);
    }
}

