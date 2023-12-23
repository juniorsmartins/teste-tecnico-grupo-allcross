package br.com.desafiogrupoallcross.config.exception.http_400;

public final class DadoComTamanhoMaximoInvalidoException extends RequisicaoMalFormuladaException {

    public DadoComTamanhoMaximoInvalidoException(String mensagem) {
        super(mensagem);
    }

    public DadoComTamanhoMaximoInvalidoException(String nomeCampo, int limiteMaximo, int quantiaCaracteres) {
        this(String.format("O campo %s possui limite de %s caracteres, mas você enviou com %s.", nomeCampo, limiteMaximo, quantiaCaracteres));
    }
}

