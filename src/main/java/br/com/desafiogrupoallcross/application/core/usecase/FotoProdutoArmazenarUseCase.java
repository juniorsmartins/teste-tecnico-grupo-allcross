package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoRecuperar;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoArmazenarInputPort;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoArmazenarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.FotoProdutoCadastrarUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class FotoProdutoArmazenarUseCase implements FotoProdutoArmazenarInputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoArmazenarUseCase.class);

    private final FotoProdutoArmazenarOutputPort outputPort;

    public FotoProdutoArmazenarUseCase(FotoProdutoArmazenarOutputPort armazenarOutputPort) {
        this.outputPort = armazenarOutputPort;
    }

    @Override
    public void armazenar(Long id, FotoProdutoRecuperar fotoProduto) {

        log.info("Iniciado serviço para cadastrar imagem de Produto com Id: {}.", id);

        Optional.ofNullable(fotoProduto)
                .ifPresentOrElse(foto -> outputPort.armazenar(id, foto),
                        () -> {throw new FotoProdutoCadastrarUseCaseException();}
                );

        log.info("Finalizado serviço para cadastrar imagem de Produto com Id: {}.", id);
    }
}

