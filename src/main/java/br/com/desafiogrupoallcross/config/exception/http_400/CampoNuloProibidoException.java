package br.com.desafiogrupoallcross.config.exception.http_400;

public final class CampoNuloProibidoException extends RequisicaoMalFormuladaException {

    public CampoNuloProibidoException(String nomeCampo) {
        super(String.format("O campo %s não pode ser nulo.", nomeCampo));
    }
}

