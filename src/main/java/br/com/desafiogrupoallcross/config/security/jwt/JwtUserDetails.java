package br.com.desafiogrupoallcross.config.security.jwt;

import br.com.desafiogrupoallcross.config.security.entity.UsuarioEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

// Classe responsável por criar um objeto para armazenar informações do usuário logado
public class JwtUserDetails extends User {

    private final UsuarioEntity usuarioEntity;

    public JwtUserDetails(UsuarioEntity usuario) {
        super(usuario.getUsername(), usuario.getPassword(), AuthorityUtils.createAuthorityList(usuario.getRole().name()));
        this.usuarioEntity = usuario;
    }

    public Long getId() {
        return this.usuarioEntity.getId();
    }

    public String getRole() {
        return this.usuarioEntity.getRole().name();
    }
}

