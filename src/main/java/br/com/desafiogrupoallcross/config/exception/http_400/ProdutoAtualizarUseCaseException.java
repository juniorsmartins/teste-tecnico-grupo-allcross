package br.com.desafiogrupoallcross.config.exception.http_400;

public final class ProdutoAtualizarUseCaseException extends RequisicaoMalFormuladaException {

    public ProdutoAtualizarUseCaseException(String mensagem) {
        super(mensagem);
    }

    public ProdutoAtualizarUseCaseException() {
        this("Falha no serviço de atualizar produto.");
    }
}

