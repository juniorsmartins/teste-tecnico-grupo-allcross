package br.com.desafiogrupoallcross.config.exception.http_500;

public final class JasperReportUseCaseException extends ProblemaInternoNoServidorException {

    public JasperReportUseCaseException(String mensagem) {
        super(mensagem);
    }

    public JasperReportUseCaseException() {
        this("Falha ao gerar PDF de Relatório com JasperReport.");
    }
}

