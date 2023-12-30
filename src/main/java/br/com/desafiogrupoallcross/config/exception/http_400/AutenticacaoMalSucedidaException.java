package br.com.desafiogrupoallcross.config.exception.http_400;

public final class AutenticacaoMalSucedidaException extends RequisicaoMalFormuladaException {

    public AutenticacaoMalSucedidaException(String username) {
        super(String.format("O Usuário 'username = %s' falhou na autenticação.", username));
    }
}

