package br.com.desafiogrupoallcross.config.exception.http_404;

import java.io.Serial;

public final class FotoProdutoNaoEncontradoException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public FotoProdutoNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public FotoProdutoNaoEncontradoException(Long id) {
    this(String.format("O FotoProduto %s não foi encontrado.", id));
  }
}

