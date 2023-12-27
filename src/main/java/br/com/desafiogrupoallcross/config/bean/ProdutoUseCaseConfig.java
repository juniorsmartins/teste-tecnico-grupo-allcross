package br.com.desafiogrupoallcross.config.bean;

import br.com.desafiogrupoallcross.adapter.out.ProdutoDeletarAdapter;
import br.com.desafiogrupoallcross.adapter.out.ProdutoInverterStatusAtivoAdapter;
import br.com.desafiogrupoallcross.adapter.out.ProdutoPesquisarAdapter;
import br.com.desafiogrupoallcross.adapter.out.ProdutoSalvarAdapter;
import br.com.desafiogrupoallcross.application.core.usecase.ProdutoCadastrarUseCase;
import br.com.desafiogrupoallcross.application.core.usecase.ProdutoDeletarUseCase;
import br.com.desafiogrupoallcross.application.core.usecase.ProdutoInverterStatusAtivoUseCase;
import br.com.desafiogrupoallcross.application.core.usecase.ProdutoPesquisarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoUseCaseConfig {

    @Bean
    public ProdutoCadastrarUseCase produtoCadastrarUseCase(ProdutoSalvarAdapter produtoSalvarAdapter) {
        return new ProdutoCadastrarUseCase(produtoSalvarAdapter);
    }

    @Bean
    public ProdutoPesquisarUseCase produtoPesquisarUseCase(ProdutoPesquisarAdapter produtoPesquisarAdapter) {
        return new ProdutoPesquisarUseCase(produtoPesquisarAdapter);
    }

    @Bean
    public ProdutoInverterStatusAtivoUseCase produtoInverterStatusAtivoUseCase(ProdutoInverterStatusAtivoAdapter inverterStatusAtivoAdapter) {
        return new ProdutoInverterStatusAtivoUseCase(inverterStatusAtivoAdapter);
    }

    @Bean
    public ProdutoDeletarUseCase produtoDeletarUseCase(ProdutoDeletarAdapter produtoDeletarAdapter) {
        return new ProdutoDeletarUseCase(produtoDeletarAdapter);
    }
}

