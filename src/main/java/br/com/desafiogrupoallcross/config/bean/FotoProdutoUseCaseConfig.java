package br.com.desafiogrupoallcross.config.bean;

import br.com.desafiogrupoallcross.adapter.out.FotoProdutoArmazenarAdapter;
import br.com.desafiogrupoallcross.adapter.out.FotoProdutoListarAdapter;
import br.com.desafiogrupoallcross.adapter.out.FotoProdutoConsultarPorIdAdapter;
import br.com.desafiogrupoallcross.adapter.out.FotoProdutoSalvarAdapter;
import br.com.desafiogrupoallcross.application.core.usecase.FotoProdutoArmazenarUseCase;
import br.com.desafiogrupoallcross.application.core.usecase.FotoProdutoCadastrarUseCase;
import br.com.desafiogrupoallcross.application.core.usecase.FotoProdutoListarUseCase;
import br.com.desafiogrupoallcross.application.core.usecase.FotoProdutoConsultarPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FotoProdutoUseCaseConfig {

    @Bean
    public FotoProdutoCadastrarUseCase fotoProdutoCadastrarUseCase(FotoProdutoSalvarAdapter salvarAdapter) {
        return new FotoProdutoCadastrarUseCase(salvarAdapter);
    }

    @Bean
    public FotoProdutoArmazenarUseCase fotoProdutoArmazenarUseCase(FotoProdutoArmazenarAdapter fotoProdutoArmazenarAdapter) {
        return new FotoProdutoArmazenarUseCase(fotoProdutoArmazenarAdapter);
    }

    @Bean
    public FotoProdutoConsultarPorIdUseCase fotoProdutoRecuperarUseCase(FotoProdutoConsultarPorIdAdapter fotoProdutoRecuperarAdapter) {
        return new FotoProdutoConsultarPorIdUseCase(fotoProdutoRecuperarAdapter);
    }

    @Bean
    public FotoProdutoListarUseCase fotoProdutoListarUseCase(FotoProdutoListarAdapter fotoProdutoListarAdapter) {
        return new FotoProdutoListarUseCase(fotoProdutoListarAdapter);
    }
}

