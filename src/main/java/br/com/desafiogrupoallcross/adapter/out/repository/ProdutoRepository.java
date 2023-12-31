package br.com.desafiogrupoallcross.adapter.out.repository;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>,
        JpaSpecificationExecutor<ProdutoEntity>, RevisionRepository<ProdutoEntity, Long, Long> { }

