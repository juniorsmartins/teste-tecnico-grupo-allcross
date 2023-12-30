package br.com.desafiogrupoallcross.utilitarios;

import br.com.desafiogrupoallcross.config.security.dtoin.UsuarioLoginDtoIn;
import br.com.desafiogrupoallcross.config.security.jwt.JwtToken;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.function.Consumer;

public class JwtAuthentication {

    public static Consumer<HttpHeaders> getHeaderAuthorization(WebTestClient webTestClient,
                                                               String username,
                                                               String password) {
        String token = webTestClient.post()
                .uri("/api/v1/auth")
                .bodyValue(new UsuarioLoginDtoIn(username, password))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody().getToken();

        return httpHeaders -> httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }
}

