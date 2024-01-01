package br.com.desafiogrupoallcross.adapter.out.repository;

import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoProdutoRepository extends JpaRepository<FotoProdutoEntity, Long> {

    List<FotoProdutoEntity> findByProdutoId(Long produtoId);

    @Query("SELECT new FotoProdutoEntity(f.id, f.nome, f.tipo, f.tamanho, f.produto) FROM FotoProdutoEntity f ORDER BY f.id")
    List<FotoProdutoEntity> listar();
}

