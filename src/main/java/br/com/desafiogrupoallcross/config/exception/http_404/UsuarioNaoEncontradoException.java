package br.com.desafiogrupoallcross.config.exception.http_404;

import br.com.desafiogrupoallcross.config.exception.http_404.RecursoNaoEncontradoException;

import java.io.Serial;

public final class UsuarioNaoEncontradoException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public UsuarioNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public UsuarioNaoEncontradoException(Long id) {
    this(String.format("O Usuário %d não foi encontrado.", id));
  }
}

