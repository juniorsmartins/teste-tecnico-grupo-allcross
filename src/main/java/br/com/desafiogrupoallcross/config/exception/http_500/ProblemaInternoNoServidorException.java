package br.com.desafiogrupoallcross.config.exception.http_500;

import java.io.Serial;

public abstract sealed class ProblemaInternoNoServidorException extends RuntimeException
  permits JasperReportUseCaseException, JasperReportControllerException {

  @Serial
  private static final long serialVersionUID = 1L;

  protected ProblemaInternoNoServidorException(String mensagem) {
    super(mensagem);
  }
}

