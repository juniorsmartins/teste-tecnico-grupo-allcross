package br.com.desafiogrupoallcross.config.exception.http_400;

public final class ProdutoDeletarUseCaseException extends RequisicaoMalFormuladaException {

    public ProdutoDeletarUseCaseException(String mensagem) {
        super(mensagem);
    }

    public ProdutoDeletarUseCaseException() {
        this("Falha no serviço de deletar produto.");
    }
}

