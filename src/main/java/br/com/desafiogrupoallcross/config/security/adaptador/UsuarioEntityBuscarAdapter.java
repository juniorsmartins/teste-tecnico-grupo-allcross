package br.com.desafiogrupoallcross.config.security.adaptador;

import br.com.desafiogrupoallcross.config.exception.http_404.UsuarioNaoEncontradoException;
import br.com.desafiogrupoallcross.config.security.entity.UsuarioEntity;
import br.com.desafiogrupoallcross.config.security.papeis.RoleEnum;
import br.com.desafiogrupoallcross.config.security.portas.out.UsuarioEntityBuscarOutputPort;
import br.com.desafiogrupoallcross.config.security.repositorio.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UsuarioEntityBuscarAdapter implements UsuarioEntityBuscarOutputPort {

    private final UsuarioRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UsuarioEntity buscarPorId(Long id) {

        return this.repository.findById(id)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioEntity buscarPorUsername(String username) {

        return this.repository.findByUsername(username)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(String
                .format("O Usuário %s não foi encontrado.", username)));
    }

    @Transactional(readOnly = true)
    @Override
    public RoleEnum buscarRolePorUsername(String username) {

        return this.repository.findRoleByUsername(username)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(String
                .format("O Usuário %s não foi encontrado.", username)));
    }
}

