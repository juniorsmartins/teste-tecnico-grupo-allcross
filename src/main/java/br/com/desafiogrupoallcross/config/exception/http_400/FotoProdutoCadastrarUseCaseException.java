package br.com.desafiogrupoallcross.config.exception.http_400;

public final class FotoProdutoCadastrarUseCaseException extends RequisicaoMalFormuladaException {

    public FotoProdutoCadastrarUseCaseException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoCadastrarUseCaseException() {
        this("Falha no useCase de cadastrar fotoProduto.");
    }
}

