package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.port.in.JasperReportInputPort;
import br.com.desafiogrupoallcross.config.exception.http_500.JasperReportUseCaseException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class JasperReportUseCase implements JasperReportInputPort {

    private static final Logger log = LoggerFactory.getLogger(JasperReportUseCase.class);

    private static final String JASPER_DIRETORIO = "classpath:jasper_reports/";

    private final ResourceLoader resourceLoader;

    private final DataSource dataSource;

    private Map<String, Object> parametros = new HashMap<>();

    public JasperReportUseCase(ResourceLoader resourceLoader,
                               DataSource dataSource) {
        this.resourceLoader = resourceLoader;
        this.dataSource = dataSource;
    }

    @Override
    public void addParams() {
        this.parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
    }

    @Override
    public byte[] gerarPdf(String nomeRelatorio) {

        log.info("Iniciado serviço JasperReport para gerar relatório PDF de Produtos.");

        byte[] bytes = null;
        try {
            Resource resource = resourceLoader.getResource(JASPER_DIRETORIO.concat(nomeRelatorio));
            InputStream stream = resource.getInputStream();
            JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parametros, dataSource.getConnection());
            bytes = JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (IOException | JRException | SQLException ex) {
            log.error("JasperReports: ", ex.getCause());
            throw new JasperReportUseCaseException();
        }

        log.info("Finalizado serviço JasperReport para gerar relatório PDF de Produtos.");

        return bytes;
    }
}

