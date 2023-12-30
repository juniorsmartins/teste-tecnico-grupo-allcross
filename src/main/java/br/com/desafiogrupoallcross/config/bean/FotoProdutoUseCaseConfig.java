package br.com.desafiogrupoallcross.config.bean;

import br.com.desafiogrupoallcross.adapter.out.FotoProdutoBuscarTodosAdapter;
import br.com.desafiogrupoallcross.adapter.out.FotoProdutoSalvarAdapter;
import br.com.desafiogrupoallcross.application.core.usecase.FotoProdutoBuscarTodosUseCase;
import br.com.desafiogrupoallcross.application.core.usecase.FotoProdutoCadastrarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FotoProdutoUseCaseConfig {

    @Bean
    public FotoProdutoCadastrarUseCase fotoProdutoCadastrarUseCase(FotoProdutoSalvarAdapter salvarAdapter) {
        return new FotoProdutoCadastrarUseCase(salvarAdapter);
    }

    @Bean
    public FotoProdutoBuscarTodosUseCase fotoProdutoBuscarTodosUseCase(FotoProdutoBuscarTodosAdapter buscarTodosAdapter) {
        return new FotoProdutoBuscarTodosUseCase(buscarTodosAdapter);
    }
}

