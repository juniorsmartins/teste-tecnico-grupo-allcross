package br.com.desafiogrupoallcross.config.security.repositorio;

import br.com.desafiogrupoallcross.config.security.entity.UsuarioEntity;
import br.com.desafiogrupoallcross.config.security.papeis.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByUsername(String username);

    Optional<RoleEnum> findRoleByUsername(String username);
}

