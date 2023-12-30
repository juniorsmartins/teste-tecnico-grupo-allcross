package br.com.desafiogrupoallcross.config.security.portas.out;

import br.com.desafiogrupoallcross.config.security.entity.UsuarioEntity;
import br.com.desafiogrupoallcross.config.security.papeis.RoleEnum;

public interface UsuarioEntityBuscarOutputPort {

    UsuarioEntity buscarPorId(Long id);

    UsuarioEntity buscarPorUsername(String username);

    RoleEnum buscarRolePorUsername(String username);
}

