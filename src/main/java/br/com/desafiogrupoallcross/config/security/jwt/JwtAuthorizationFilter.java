package br.com.desafiogrupoallcross.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Classe responsável por capturar todas as requisições que chegam na API Rest. Pega o Token para validar o token e autenticar o usuário na aplicação.
// Se o Token não for validado, então o Security interrompe a operação e devolve erro para o cliente
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override // Esse método que irá interceptar as requisições
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Passo 1 - recuperar o Token do cabeçalho da requisição
        final String token = request.getHeader(JwtUtils.JWT_AUTHORIZATION);

        // Testar o conteúdo do Token - primeiro ver se é nulo ou não possui 'Bearer '
        if (token == null || !token.startsWith(JwtUtils.JWT_BEARER)) {
            log.info("JWT Token está nulo, vazio ou não iniciado com 'Bearer '.");
            filterChain.doFilter(request, response); // Isso devolve o request e o response para o processo de requisição. Pois podem ser alterados adicionando informações.
            return; // Sai do doFilterInternal
        }

        // Testar a validade do Token - caso seja inválido, sai do doFilterInternal
        if (!JwtUtils.isTokenValid(token)) {
            log.warn("JWT Token está inválido ou expirado.");
            filterChain.doFilter(request, response);
            return; // Sai do doFilterInternal
        }

        String username = JwtUtils.getUsernameFromToken(token); // Recupera o username do Usuário a partir do Token

        toAuthentication(request, username);

        filterChain.doFilter(request, response);
    }

    // Método para autenticar o usuário e entregá-lo ao contexto de segurança da aplicação
    private void toAuthentication(HttpServletRequest request, String username) {
        // Consultar o database pelo username do Usuário
        UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

        // Este é um método estático que cria uma instância de UsernamePasswordAuthenticationToken, que representa um usuário autenticado.
        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken
            .authenticated(userDetails, null, userDetails.getAuthorities());

        // Passando o objeto de requisição (request) para a parte de autenticação do Security. Assim ele
        // consegue unir duas operações, a parte de segurança com as informações da requisição.
        authenticationToken.setDetails(new WebAuthenticationDetailsSource()
            .buildDetails(request));

        // SecurityContextHolder é a classe que nos dá acesso ao contexto de segurança da aplicação.
        // Assim passamos todas as informações necessárias ao contexto de segurança sobre quem é o usuário autenticado
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}

