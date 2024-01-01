package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoListarInputPort;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoListarOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FotoProdutoListarUseCase implements FotoProdutoListarInputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoListarUseCase.class);

    private final FotoProdutoListarOutputPort fotoProdutoListarOutputPort;

    public FotoProdutoListarUseCase(FotoProdutoListarOutputPort fotoProdutoListarOutputPort) {
        this.fotoProdutoListarOutputPort = fotoProdutoListarOutputPort;
    }

    @Override
    public List<FotoProduto> listar() {

        log.info("");

        var resposta = this.fotoProdutoListarOutputPort.listar();

        log.info("");

        return resposta;
    }
}

