package br.com.desafiogrupoallcross.config.exception.http_400;

public final class ProdutoSalvarAdapterException extends RequisicaoMalFormuladaException {

    public ProdutoSalvarAdapterException(String mensagem) {
        super(mensagem);
    }

    public ProdutoSalvarAdapterException() {
        this("Falha ao tentar salvar produto no banco de dados.");
    }
}

