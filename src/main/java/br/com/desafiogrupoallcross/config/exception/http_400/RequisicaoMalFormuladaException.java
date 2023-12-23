package br.com.desafiogrupoallcross.config.exception.http_400;

import java.io.Serial;

public abstract sealed class RequisicaoMalFormuladaException extends RuntimeException permits FalhaAoSalvarProdutoException {

  @Serial
  private static final long serialVersionUID = 1L;

  protected RequisicaoMalFormuladaException(String mensagem) {
    super(mensagem);
  }
}

