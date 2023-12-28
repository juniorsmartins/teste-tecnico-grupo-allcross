package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.ProdutoCadastrarInputPort;
import br.com.desafiogrupoallcross.application.port.out.ProdutoSalvarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoCadastrarUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ProdutoCadastrarUseCase implements ProdutoCadastrarInputPort {

    private static final Logger log = LoggerFactory.getLogger(ProdutoCadastrarUseCase.class);

    private final ProdutoSalvarOutputPort salvarOutputPort;

    public ProdutoCadastrarUseCase(ProdutoSalvarOutputPort salvarOutputPort) {
        this.salvarOutputPort = salvarOutputPort;
    }

    @Override
    public ProdutoBusiness cadastrar(ProdutoBusiness produtoBusiness) {

        log.info("Iniciado serviço para cadastrar Produto com nome: {}.", produtoBusiness.getNome());

        var resposta = Optional.ofNullable(produtoBusiness)
                .map(salvarOutputPort::salvar)
                .orElseThrow(ProdutoCadastrarUseCaseException::new);

        log.info("Finalizado serviço para cadastrar Produto com nome: {}.", resposta.getNome());

        return resposta;
    }
}

