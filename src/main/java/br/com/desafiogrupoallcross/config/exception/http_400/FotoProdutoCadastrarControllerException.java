package br.com.desafiogrupoallcross.config.exception.http_400;

public final class FotoProdutoCadastrarControllerException extends RequisicaoMalFormuladaException {

    public FotoProdutoCadastrarControllerException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoCadastrarControllerException() {
        this("Falha no controle de cadastrar fotoProduto.");
    }
}

