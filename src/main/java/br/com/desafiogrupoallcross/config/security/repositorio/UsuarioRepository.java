package br.com.desafiogrupoallcross.config.security.repositorio;

import br.com.desafiogrupoallcross.config.security.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> { }

