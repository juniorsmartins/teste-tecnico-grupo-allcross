package br.com.desafiogrupoallcross.config.bean;

import br.com.desafiogrupoallcross.adapter.out.*;
import br.com.desafiogrupoallcross.application.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditoriaProdutoUseCaseConfig {

    @Bean
    public AuditoriaProdutoUseCase auditoriaProdutoUseCase(AuditoriaProdutoAdapter auditoriaProdutoAdapter) {
        return new AuditoriaProdutoUseCase(auditoriaProdutoAdapter);
    }
}

