package br.com.desafiogrupoallcross.config.exception.http_500;

public final class FalhaNaCompactacaoDeImagemException extends ProblemaInternoNoServidorException {

    public FalhaNaCompactacaoDeImagemException(String mensagem) {
        super(mensagem);
    }

    public FalhaNaCompactacaoDeImagemException() {
        this("Falha no serviço de compactar Imagem.");
    }
}

