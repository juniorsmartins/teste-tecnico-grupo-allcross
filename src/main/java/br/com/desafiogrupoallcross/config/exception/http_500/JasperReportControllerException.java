package br.com.desafiogrupoallcross.config.exception.http_500;

public final class JasperReportControllerException extends ProblemaInternoNoServidorException {

    public JasperReportControllerException(String mensagem) {
        super(mensagem);
    }

    public JasperReportControllerException() {
        this("Falha no controlador ao gerar PDF de Relatório com JasperReport.");
    }
}

