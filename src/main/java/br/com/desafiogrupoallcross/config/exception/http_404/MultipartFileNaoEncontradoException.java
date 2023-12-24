package br.com.desafiogrupoallcross.config.exception.http_404;

import java.io.Serial;

public final class MultipartFileNaoEncontradoException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public MultipartFileNaoEncontradoException(String mensagem) {
    super(mensagem);
  }

  public MultipartFileNaoEncontradoException() {
    this("MultipartFile não pode ser nulo ou vazio na conversão.");
  }
}

