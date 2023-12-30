package br.com.desafiogrupoallcross.config.security.controller;

import br.com.desafiogrupoallcross.config.exception.http_400.AutenticacaoMalSucedidaException;
import br.com.desafiogrupoallcross.config.security.dtoin.UsuarioLoginDtoIn;
import br.com.desafiogrupoallcross.config.security.jwt.JwtToken;
import br.com.desafiogrupoallcross.config.security.jwt.JwtUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api/v1"})
@RequiredArgsConstructor
public class AutenticacaoController {

    private final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);

    private final JwtUserDetailsService jwtUserDetailsService;

    private final AuthenticationManager authenticationManager;

    @PostMapping(path = {"/auth"})
    public ResponseEntity<JwtToken> autenticar(@RequestBody @Valid UsuarioLoginDtoIn loginDtoIn, HttpServletRequest request) {
        var username = loginDtoIn.username();

        log.info("Recebida requisição para autenticar Usuário: {}", username);

        JwtToken token = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, loginDtoIn.password()); // Processo do Security - confirma existência no database
            this.authenticationManager.authenticate(authenticationToken); // Processo do Security

            token = this.jwtUserDetailsService.getTokenAuthenticated(username);

        } catch (AuthenticationException ex) {
            log.warn("Usuário não autenticado com username: {}", username);
            throw new AutenticacaoMalSucedidaException(username);
        }

        log.info("Usuário autenticado com sucesso: {}", username);

        return ResponseEntity
                .ok()
                .body(token);
    }
}

