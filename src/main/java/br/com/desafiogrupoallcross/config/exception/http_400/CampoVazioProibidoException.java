package br.com.desafiogrupoallcross.config.exception.http_400;

public final class CampoVazioProibidoException extends RequisicaoMalFormuladaException {

    public CampoVazioProibidoException(String nomeCampo) {
        super(String.format("O campo %s não pode ser vazio ou em branco.", nomeCampo));
    }
}

