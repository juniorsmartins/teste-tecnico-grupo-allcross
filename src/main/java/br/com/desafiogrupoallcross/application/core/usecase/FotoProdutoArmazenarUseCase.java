package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoArmazenarInputPort;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoArmazenarOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class FotoProdutoArmazenarUseCase implements FotoProdutoArmazenarInputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoArmazenarUseCase.class);

    private final FotoProdutoArmazenarOutputPort outputPort;

    public FotoProdutoArmazenarUseCase(FotoProdutoArmazenarOutputPort armazenarOutputPort) {
        this.outputPort = armazenarOutputPort;
    }

    @Override
    public void armazenar(final Long id, final FotoProduto fotoProduto) {

        log.info("Iniciado serviço para cadastrar imagem de Produto com Id: {}.", id);

        Objects.requireNonNull(id);
        Objects.requireNonNull(fotoProduto);

        this.outputPort.armazenar(id, fotoProduto);

        log.info("Finalizado serviço para cadastrar imagem de Produto com Id: {}.", id);
    }
}

