package br.com.desafiogrupoallcross.config.bean;

import br.com.desafiogrupoallcross.application.core.usecase.JasperReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

@Configuration
public class JasperReportConfig {

    @Bean
    public JasperReportUseCase jasperReportUseCase(ResourceLoader resourceLoader, DataSource dataSource) {
        return new JasperReportUseCase(resourceLoader, dataSource);
    }
}

