package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoCadastrarInputPort;

public class FotoProdutoCadastrarUseCase implements FotoProdutoCadastrarInputPort {

    @Override
    public void cadastrarImagem(Long id, FotoProdutoBusiness fotoProdutoBusiness) {

        System.out.println("\n id: " + id);
    }
}

