package br.com.desafiogrupoallcross.application.port.in;

public interface JasperReportInputPort {

    void addParams();

    byte[] gerarPdf(String nomeRelatorio);
}

