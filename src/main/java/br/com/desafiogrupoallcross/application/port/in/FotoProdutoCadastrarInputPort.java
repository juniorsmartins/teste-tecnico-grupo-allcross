package br.com.desafiogrupoallcross.application.port.in;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;

public interface FotoProdutoCadastrarInputPort {

    void cadastrarImagem(Long id, FotoProdutoBusiness fotoProdutoBusiness);
}

