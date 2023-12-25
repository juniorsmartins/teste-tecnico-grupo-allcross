package br.com.desafiogrupoallcross.adapter.out.spec;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class ProdutoSpecification {

    public static Specification<ProdutoEntity> consultarDinamicamente(ProdutoFiltro filtro) {

        return ((root, query, criteriaBuilder) -> {

            var parametrosDePesquisa = new ArrayList<Predicate>();

            if (ObjectUtils.isNotEmpty(filtro.getNome())) {
                var parametros = Arrays.asList(filtro.getNome().split(","));
                List<Predicate> parametrosPredicate = parametros.stream()
                        .map(parametro -> criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nome")), "%" + parametro.toLowerCase() + "%"))
                        .toList();
                parametrosDePesquisa.add(criteriaBuilder.or(parametrosPredicate.toArray(new Predicate[0])));
            }

            return criteriaBuilder.and(parametrosDePesquisa.toArray(new Predicate[0]));
        });
    }
}

