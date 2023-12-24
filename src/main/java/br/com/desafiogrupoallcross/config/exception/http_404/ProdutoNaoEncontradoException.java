package br.com.desafiogrupoallcross.config.exception.http_404;

import java.io.Serial;

public final class ProdutoNaoEncontradoException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public ProdutoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public ProdutoNaoEncontradoException(Long id) {
    this(String.format("O Produto %s não foi encontrado.", id));
  }
}

