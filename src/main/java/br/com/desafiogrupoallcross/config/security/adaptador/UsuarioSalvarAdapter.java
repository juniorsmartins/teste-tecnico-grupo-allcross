package br.com.desafiogrupoallcross.config.security.adaptador;

import br.com.desafiogrupoallcross.config.security.dominio.UsuarioBusiness;
import br.com.desafiogrupoallcross.config.security.portas.out.UsuarioSalvarOutputPort;
import br.com.desafiogrupoallcross.config.security.repositorio.UsuarioRepository;
import br.com.desafiogrupoallcross.config.security.utils.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioSalvarAdapter implements UsuarioSalvarOutputPort {

    private final UsuarioRepository repository;

    private final Util util;

    @Override
    public UsuarioBusiness salvar(UsuarioBusiness usuarioBusiness) {

        return Optional.ofNullable(usuarioBusiness)
                .map(this.util::converterUsuarioBusinessParaEntity)
                .map(this.repository::save)
                .map(this.util::converterUsuarioEntityParaBusiness)
                .orElseThrow();
    }
}

