package br.com.desafiogrupoallcross.config.exception.http_400;

public final class ProdutoCadastrarUseCaseException extends RequisicaoMalFormuladaException {

    public ProdutoCadastrarUseCaseException(String mensagem) {
        super(mensagem);
    }

    public ProdutoCadastrarUseCaseException() {
        this("Falha no serviço de cadastrar produto.");
    }
}

