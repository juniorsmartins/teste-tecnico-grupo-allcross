package br.com.desafiogrupoallcross.config.exception.http_400;

public final class ProdutoInverterStatusAtivoUseCaseException extends RequisicaoMalFormuladaException {

    public ProdutoInverterStatusAtivoUseCaseException(String mensagem) {
        super(mensagem);
    }

    public ProdutoInverterStatusAtivoUseCaseException() {
        this("Falha no serviço de inverter status ativo de produto.");
    }
}

