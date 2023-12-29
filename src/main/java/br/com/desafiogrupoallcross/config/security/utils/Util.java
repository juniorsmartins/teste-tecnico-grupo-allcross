package br.com.desafiogrupoallcross.config.security.utils;

import br.com.desafiogrupoallcross.config.security.dominio.UsuarioBusiness;
import br.com.desafiogrupoallcross.config.security.entity.UsuarioEntity;

public interface Util {

    UsuarioEntity converterUsuarioBusinessParaEntity(UsuarioBusiness usuarioBusiness);

    UsuarioBusiness converterUsuarioEntityParaBusiness(UsuarioEntity usuarioEntity);
}

