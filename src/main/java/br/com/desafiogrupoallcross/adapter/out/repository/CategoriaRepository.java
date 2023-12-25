package br.com.desafiogrupoallcross.adapter.out.repository;

import br.com.desafiogrupoallcross.adapter.out.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> { }

