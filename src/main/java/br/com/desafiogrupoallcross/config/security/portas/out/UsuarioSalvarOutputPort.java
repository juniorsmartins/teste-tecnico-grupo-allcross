package br.com.desafiogrupoallcross.config.security.portas.out;

import br.com.desafiogrupoallcross.config.security.dominio.UsuarioBusiness;

public interface UsuarioSalvarOutputPort {

    UsuarioBusiness salvar(UsuarioBusiness usuarioBusiness);
}

