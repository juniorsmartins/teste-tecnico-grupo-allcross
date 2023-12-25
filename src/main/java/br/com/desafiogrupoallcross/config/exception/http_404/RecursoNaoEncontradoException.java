package br.com.desafiogrupoallcross.config.exception.http_404;

import java.io.Serial;

public abstract sealed class RecursoNaoEncontradoException extends RuntimeException permits ProdutoNaoEncontradoException,
        MultipartFileNaoEncontradoException, CategoriaNaoEncontradaException {

  @Serial
  private static final long serialVersionUID = 1L;

  public RecursoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }
}

