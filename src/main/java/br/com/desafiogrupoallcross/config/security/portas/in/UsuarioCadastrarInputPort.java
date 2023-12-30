package br.com.desafiogrupoallcross.config.security.portas.in;

import br.com.desafiogrupoallcross.config.security.dominio.UsuarioBusiness;

public interface UsuarioCadastrarInputPort {

    UsuarioBusiness cadastrar(UsuarioBusiness usuarioBusiness);
}

