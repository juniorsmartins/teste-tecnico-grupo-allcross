package br.com.desafiogrupoallcross.config.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class SpringJpaAuditingConfig implements AuditorAware<String> { // Usamos String no T do AuditorAware por usarmos username do Usuário. Poderia ser Long se estivéssemos usando Id do Usuário.

    @Override
    public Optional<String> getCurrentAuditor() {
        var authentication = SecurityContextHolder.getContext().getAuthentication(); // Pega o usuário do contexto de segurança

        if (authentication != null && authentication.isAuthenticated()) {
            return Optional.of(authentication.getName()); // Pega o username do usuário
        }

        return null;
    }
}

