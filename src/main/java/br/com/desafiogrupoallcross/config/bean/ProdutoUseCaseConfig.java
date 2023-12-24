package br.com.desafiogrupoallcross.config.bean;

import br.com.desafiogrupoallcross.adapter.out.ProdutoSalvarAdapter;
import br.com.desafiogrupoallcross.application.core.usecase.ProdutoCadastrarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoUseCaseConfig {

    @Bean
    public ProdutoCadastrarUseCase produtoCadastrarUseCase(ProdutoSalvarAdapter produtoSalvarAdapter) {
        return new ProdutoCadastrarUseCase(produtoSalvarAdapter);
    }
}

