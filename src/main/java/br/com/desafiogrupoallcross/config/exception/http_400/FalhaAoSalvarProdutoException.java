package br.com.desafiogrupoallcross.config.exception.http_400;

public final class FalhaAoSalvarProdutoException extends RequisicaoMalFormuladaException {

    public FalhaAoSalvarProdutoException(String mensagem) {
        super(mensagem);
    }

    public FalhaAoSalvarProdutoException() {
        this("Falha ao tentar salvar produto no banco de dados.");
    }
}

