package br.com.desafiogrupoallcross.config.exception.http_400;

public final class ProdutoCadastrarControllerException extends RequisicaoMalFormuladaException {

    public ProdutoCadastrarControllerException(String mensagem) {
        super(mensagem);
    }

    public ProdutoCadastrarControllerException() {
        this("Falha no controle de cadastrar produto.");
    }
}

