package br.com.desafiogrupoallcross.config.security.jwt;

import br.com.desafiogrupoallcross.config.security.papeis.RoleEnum;
import br.com.desafiogrupoallcross.config.security.portas.out.UsuarioEntityBuscarOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService { // Classe responsável por localizar usuário no database

    private final UsuarioEntityBuscarOutputPort usuarioEntityBuscarOutputPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // Consultará o usuário por username no database - E devolverá o usuário no formato UserDetails
        var usuario = this.usuarioEntityBuscarOutputPort.buscarPorUsername(username);
        return new JwtUserDetails(usuario);
    }

    public JwtToken getTokenAuthenticated(String username) { // Usado quando o cliente for autenticar na aplicação. Então ele envia username/password para autenticar, isso dando certo, é gerado um Token JWT
        RoleEnum role = this.usuarioEntityBuscarOutputPort.buscarRolePorUsername(username);
        var roleStringSemRole_ = role.name().substring("ROLE_".length());
        return JwtUtils.createToken(username, roleStringSemRole_);
    }
}

