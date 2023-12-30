package br.com.desafiogrupoallcross.config.security.configuration;

import br.com.desafiogrupoallcross.config.security.adaptador.UsuarioSalvarAdapter;
import br.com.desafiogrupoallcross.config.security.servico.UsuarioCadastrarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UsuarioUseCaseConfig {

    @Bean
    public UsuarioCadastrarUseCase usuarioCadastrarUseCase(UsuarioSalvarAdapter usuarioSalvarAdapter,
                                                           PasswordEncoder passwordEncoder) {
        return new UsuarioCadastrarUseCase(usuarioSalvarAdapter, passwordEncoder);
    }
}

