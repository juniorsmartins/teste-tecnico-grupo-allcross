package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.application.port.in.JasperReportInputPort;
import br.com.desafiogrupoallcross.config.exception.http_500.JasperReportControllerException;
import br.com.desafiogrupoallcross.config.exception.http_500.JasperReportUseCaseException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = {"/api/v1/relatorios"})
@RequiredArgsConstructor
public class JasperReportController {

    private final Logger log = LoggerFactory.getLogger(JasperReportController.class);

    private final JasperReportInputPort jasperReportInputPort;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ESTOQUISTA')")
    @GetMapping(path = {"/produtos"})
    public ResponseEntity<Void> getRelatorio(HttpServletResponse httpServletResponse) {

        log.info("");

        var resposta = this.jasperReportInputPort.gerarPdf("allcross.jasper");

        httpServletResponse.setContentType(MediaType.APPLICATION_PDF_VALUE);
        httpServletResponse.setHeader("Content-disposition", "inline; filename=" + System.currentTimeMillis() + ".pdf");

        try {
            httpServletResponse.getOutputStream().write(resposta);
        } catch (IOException e) {
            throw new JasperReportControllerException();
        }

        log.info("");

        return ResponseEntity
                .noContent()
                .build();
    }
}

