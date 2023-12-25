package br.com.desafiogrupoallcross.adapter.out.repository;

import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoProdutoRepository extends JpaRepository<FotoProdutoEntity, Long> {

    List<FotoProdutoEntity> findByProdutoId(Long produtoId);
}

