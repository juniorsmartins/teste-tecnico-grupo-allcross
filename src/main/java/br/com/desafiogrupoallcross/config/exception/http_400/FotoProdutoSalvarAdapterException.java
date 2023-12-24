package br.com.desafiogrupoallcross.config.exception.http_400;

public final class FotoProdutoSalvarAdapterException extends RequisicaoMalFormuladaException {

    public FotoProdutoSalvarAdapterException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoSalvarAdapterException() {
        this("Falha no adapter de salvar fotoProduto.");
    }
}

