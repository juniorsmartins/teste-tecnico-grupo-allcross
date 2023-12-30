package br.com.desafiogrupoallcross.config.security.configuration;

import br.com.desafiogrupoallcross.config.security.jwt.JwtAuthenticationEntryPoint;
import br.com.desafiogrupoallcross.config.security.jwt.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableMethodSecurity
@EnableWebMvc
@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable) // Precisa desabilitar, pois usa Statefull (sessão). E APIs Rest usam Stateless.
                .formLogin(AbstractHttpConfigurer::disable) // Desabilitar formulário padrão de login
                .httpBasic(AbstractHttpConfigurer::disable) // Desabilitar autenticação básica - não possui segurança apropriada
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/usuarios").permitAll() // Post público para criar usuário
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth").permitAll() // Post público para o usuário logar na aplicação
                        .anyRequest().authenticated() // Todos os demais endpoints são privados e requerem autenticação
                ) // Incluir sistema de autorizações
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Define a política de sessão da API - neste caso, Stateless (sem sessão)
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class) // Registramos os filtros - primeiro acionará o jwtAuthorizationFilter() e só depois acionará o UsernamePasswordAuthenticationFilter - a ordem como foram colocados define quem será acionado primeiro
                .exceptionHandling(ex -> ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint())) // Sempre que houver uma exceção de usuário não logado, o Spring vai na classe JwtAuthenticationEntryPoint e lança a exceção com status 401
                .build();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() { // Coloca o filtro sob gerenciamento do Spring - Porém, precisa registrar sua existência no método filterChain
        return new JwtAuthorizationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Criptografia
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // Gerenciamento de Autenticação
    }
}

