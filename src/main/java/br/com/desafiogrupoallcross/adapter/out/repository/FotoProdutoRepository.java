package br.com.desafiogrupoallcross.adapter.out.repository;

import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoProdutoRepository extends JpaRepository<FotoProdutoEntity, Long> { }

