package br.com.desafiogrupoallcross.application.port.in;

import java.util.stream.Stream;

public interface FotoProdutoRecuperarInputPort {

    Stream<byte[]> recuperarFoto(Long produtoId);
}

