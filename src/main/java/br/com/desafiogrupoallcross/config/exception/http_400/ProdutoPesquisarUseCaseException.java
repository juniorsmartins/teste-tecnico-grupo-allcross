package br.com.desafiogrupoallcross.config.exception.http_400;

public final class ProdutoPesquisarUseCaseException extends RequisicaoMalFormuladaException {

    public ProdutoPesquisarUseCaseException(String mensagem) {
        super(mensagem);
    }

    public ProdutoPesquisarUseCaseException() {
        this("Falha no serviço de pesquisar produto.");
    }
}

