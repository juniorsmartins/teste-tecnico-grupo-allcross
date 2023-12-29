package br.com.desafiogrupoallcross.config.security.utils;

import br.com.desafiogrupoallcross.config.security.dominio.UsuarioBusiness;
import br.com.desafiogrupoallcross.config.security.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

@Service
public class UtilImpl implements Util {

    @Override
    public UsuarioEntity converterUsuarioBusinessParaEntity(UsuarioBusiness usuarioBusiness) {

        return UsuarioEntity.builder()
                .id(usuarioBusiness.getId())
                .username(usuarioBusiness.getUsername())
                .password(usuarioBusiness.getPassword())
                .role(usuarioBusiness.getRole()ar)
                .build();
    }

    @Override
    public UsuarioBusiness converterUsuarioEntityParaBusiness(UsuarioEntity usuarioEntity) {

        var business = new UsuarioBusiness();
        business.setId(usuarioEntity.getId());
        business.setUsername(usuarioEntity.getUsername());
        business.setPassword(usuarioEntity.getPassword());
        business.setRole(usuarioEntity.getRole());
        return business;
    }
}

