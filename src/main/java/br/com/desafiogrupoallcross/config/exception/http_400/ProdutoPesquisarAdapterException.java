package br.com.desafiogrupoallcross.config.exception.http_400;

public final class ProdutoPesquisarAdapterException extends RequisicaoMalFormuladaException {

    public ProdutoPesquisarAdapterException(String mensagem) {
        super(mensagem);
    }

    public ProdutoPesquisarAdapterException() {
        this("Falha ao tentar pesquisar produto no banco de dados.");
    }
}

