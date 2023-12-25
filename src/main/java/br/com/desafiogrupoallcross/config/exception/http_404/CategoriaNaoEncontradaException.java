package br.com.desafiogrupoallcross.config.exception.http_404;

import java.io.Serial;

public final class CategoriaNaoEncontradaException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public CategoriaNaoEncontradaException(String mensagem) {
    super(mensagem);
  }

  public CategoriaNaoEncontradaException(Long id) {
    this(String.format("A Categoria %s não foi encontrada.", id));
  }
}

