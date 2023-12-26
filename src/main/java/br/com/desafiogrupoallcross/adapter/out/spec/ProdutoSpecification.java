package br.com.desafiogrupoallcross.adapter.out.spec;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProdutoSpecification {

    public static Specification<ProdutoEntity> consultarDinamicamente(ProdutoFiltro filtro) {

        return ((root, query, criteriaBuilder) -> {

            var pesquisa = new ArrayList<Predicate>();

            if (ObjectUtils.isNotEmpty(filtro.getNome())) {
                var parametros = Arrays.asList(filtro.getNome().split(","));

                List<Predicate> predicates = parametros.stream()
                    .map(valor -> criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nome")), "%" + valor.toLowerCase() + "%"))
                    .toList();

                pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
            }

            return criteriaBuilder.and(pesquisa.toArray(new Predicate[0]));
        });
    }
}

