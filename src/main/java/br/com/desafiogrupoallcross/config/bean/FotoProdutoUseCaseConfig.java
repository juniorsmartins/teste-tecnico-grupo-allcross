package br.com.desafiogrupoallcross.config.bean;

import br.com.desafiogrupoallcross.application.core.usecase.FotoProdutoCadastrarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FotoProdutoUseCaseConfig {

    @Bean
    public FotoProdutoCadastrarUseCase fotoProdutoCadastrarUseCase() {
        return new FotoProdutoCadastrarUseCase();
    }
}

