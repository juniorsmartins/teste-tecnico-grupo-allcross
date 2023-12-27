package br.com.desafiogrupoallcross.config.exception.http_400;

public final class ProdutoAtualizarAdapterException extends RequisicaoMalFormuladaException {

    public ProdutoAtualizarAdapterException(String mensagem) {
        super(mensagem);
    }

    public ProdutoAtualizarAdapterException() {
        this("Falha ao tentar atualizar produto no banco de dados.");
    }
}

