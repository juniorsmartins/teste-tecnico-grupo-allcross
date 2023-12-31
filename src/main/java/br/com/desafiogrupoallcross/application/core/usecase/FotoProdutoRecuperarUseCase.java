package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.adapter.out.FotoProdutoRecuperarAdapter;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoRecuperar;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoRecuperarInputPort;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoRecuperarOutputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class FotoProdutoRecuperarUseCase implements FotoProdutoRecuperarInputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoRecuperarUseCase.class);

    private final FotoProdutoRecuperarOutputPort recuperarOutputPort;

    public FotoProdutoRecuperarUseCase(FotoProdutoRecuperarOutputPort recuperarOutputPort) {
        this.recuperarOutputPort = recuperarOutputPort;
    }

    @Override
    public Stream<byte[]> recuperarFoto(final Long produtoId) {

        log.info("");

        var resposta = Optional.ofNullable(produtoId)
                .map(this.recuperarOutputPort::recuperarImagem)
                .map(lista -> lista.stream().map(FotoProdutoRecuperar::getFoto))
                .orElseThrow();

        log.info("");

        return resposta;
    }
}

