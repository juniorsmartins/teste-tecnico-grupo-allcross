package br.com.desafiogrupoallcross.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer ";

    public static final String JWT_AUTHORIZATION = "Authorization"; // Chave referente ao cabeçalho do envio do token pelo cliente

    public static final String SECRET_KEY = "1239874560-9289372517-2233884466"; // Sequência de 32 caracteres (mínimo) - usada pelo processo de criptografia

    public static final long EXPIRE_DAYS = 0; // Armazena tempo para o token expirar

    public static final long EXPIRE_HOURS = 0; // Armazena tempo para o token expirar

    public static final long EXPIRE_MINUTES = 2; // Armazena tempo para o token expirar

    public JwtUtils() { }

    private static Key generateKey() { // Responsável por preparar a chave para ser criptografada ao gerar o Token
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date toExpireDate(Date start) { // Método responsável por gerar a data/hora de expiração do Token
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken(String username, String role) {
        Date issueAt = new Date(); // Data de criação do token
        Date limit = toExpireDate(issueAt); // Data limite do token

        // Criar Token JWT
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT") // Informa ser um token do tipo JWT
                .setSubject(username) // Importante ter username ou Id do usuário - Para checar se existente no database
                .setIssuedAt(issueAt) // Recebe a data de criação do Token
                .setExpiration(limit) // Recebe a data limite do Token - até quando ele é válido
                .signWith(generateKey(), SignatureAlgorithm.HS256) // Adiciona assinatura do Token - primeiro chave e depois algoritmo de criptografia
                .claim("role", role) // Pode adicionar vários Claim (chave - valor). Neste caso, vamos adicionar o papel/autorização/perfil do usuário
                .compact();

        return new JwtToken(token);
    }

    private static Claims getClaimsFromToken(String token) { // Método responsável por recuperar o conteúdo do Token - tipo descriptografia
        try {
            return Jwts.parserBuilder()
                .setSigningKey(generateKey()).build() // Verifica se a assinatura do Token é compatível com a gerada na classe
                .parseClaimsJws(refactorToken(token)).getBody(); // Recupera o corpo do Token

        } catch (JwtException ex) {
            log.error(String.format("Token inválido: %s", ex.getMessage()));
        }
        return null;
    }

    public static String getUsernameFromToken(String token) { // Método para recuperar o username de dentro do Token
        return getClaimsFromToken(token).getSubject();
    }

    public static boolean isTokenValid(String token) { // Método responsável por testar a validade do Token
        try {
            Jwts.parserBuilder()
                .setSigningKey(generateKey()).build() // Verifica se a assinatura do Token é compatível com a gerada na classe
                .parseClaimsJws(refactorToken(token));

            return true;
        } catch (JwtException ex) {
            log.error(String.format("Token inválido: %s", ex.getMessage()));
        }
        return false;
    }

    private static String refactorToken(String token) { // Método responsável por remover a instrução: "Bearer "
        if (token.contains(JWT_BEARER)) {
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }
}

