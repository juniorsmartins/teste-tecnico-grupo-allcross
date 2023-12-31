package br.com.desafiogrupoallcross.config.exception.http_500;

public final class FalhaNaDescompactacaoDeImagemException extends ProblemaInternoNoServidorException {

    public FalhaNaDescompactacaoDeImagemException(String mensagem) {
        super(mensagem);
    }

    public FalhaNaDescompactacaoDeImagemException() {
        this("Falha no serviço de descompactar Imagem.");
    }
}

