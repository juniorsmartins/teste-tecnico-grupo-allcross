package br.com.desafiogrupoallcross.config.exception.http_400;

import java.io.Serial;

public abstract sealed class RequisicaoMalFormuladaException extends RuntimeException permits ProdutoSalvarAdapterException,
        CampoNuloProibidoException, CampoVazioProibidoException, DadoComTamanhoMaximoInvalidoException,
        ProdutoCadastrarUseCaseException, ProdutoCadastrarControllerException, FotoProdutoCadastrarControllerException,
        FotoProdutoCadastrarUseCaseException, FotoProdutoSalvarAdapterException, ProdutoPesquisarAdapterException,
        ProdutoPesquisarUseCaseException, ProdutoInverterStatusAtivoUseCaseException, ProdutoDeletarUseCaseException,
        ProdutoAtualizarAdapterException, ProdutoAtualizarUseCaseException {

  @Serial
  private static final long serialVersionUID = 1L;

  protected RequisicaoMalFormuladaException(String mensagem) {
    super(mensagem);
  }
}

