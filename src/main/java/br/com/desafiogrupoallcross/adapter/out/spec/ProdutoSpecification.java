package br.com.desafiogrupoallcross.adapter.out.spec;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ProdutoSpecification {

    public static Specification<ProdutoEntity> consultarDinamicamente(ProdutoFiltro filtro) {

        return ((root, query, criteriaBuilder) -> {

            var pesquisa = new ArrayList<Predicate>();

            if (ObjectUtils.isNotEmpty(filtro.getNome())) {
                adicionarNomesPredicados(filtro.getNome(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getId())) {
                adicionarIdsPredicados(filtro.getId(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getAtivo())) {
                adicionarAtivoPredicados(filtro.getAtivo(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getValorCusto())) {
                adicionarValorCustoPredicados(filtro.getValorCusto(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getIcms())) {
                adicionarIcmsPredicados(filtro.getIcms(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getValorVenda())) {
                adicionarValorVendaPredicados(filtro.getValorVenda(), root, criteriaBuilder, pesquisa);
            }

            if (ObjectUtils.isNotEmpty(filtro.getQuantidadeEstoque())) {
                adicionarEstoquePredicados(filtro.getQuantidadeEstoque(), root, criteriaBuilder, pesquisa);
            }

            return criteriaBuilder.and(pesquisa.toArray(new Predicate[0]));
        });
    }

    private static void adicionarNomesPredicados(String nome, Root<ProdutoEntity> root,
                                                 CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var parametros = Arrays.asList(nome.trim().split(","));

        List<Predicate> predicates = parametros.stream()
            .map(valor -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("nome")), "%" + valor.toLowerCase() + "%"))
            .toList();

        pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }

    private static void adicionarIdsPredicados(String id, Root<ProdutoEntity> root,
                                               CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {

        var parametros = List.of(id.trim().split(","))
            .stream()
            .map(Long::parseLong)
            .toList();

        var predicates = parametros.stream()
            .map(valor -> criteriaBuilder.equal(root.get("id"), valor))
            .toList();

        pesquisa.add(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
    }

    private static void adicionarAtivoPredicados(boolean ativo, Root<ProdutoEntity> root,
                                               CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {
        pesquisa.add(criteriaBuilder.equal(root.get("ativo"), ativo));
    }

    private static void adicionarValorCustoPredicados(BigDecimal valorCusto, Root<ProdutoEntity> root,
                                                      CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {
        pesquisa.add(criteriaBuilder.equal(root.get("valorCusto"), valorCusto));
    }

    private static void adicionarIcmsPredicados(Double icms, Root<ProdutoEntity> root,
                                                 CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {
        pesquisa.add(criteriaBuilder.equal(root.get("icms"), icms));
    }

    private static void adicionarValorVendaPredicados(BigDecimal valorVenda, Root<ProdutoEntity> root,
                                                CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {
        pesquisa.add(criteriaBuilder.equal(root.get("valorVenda"), valorVenda));
    }

    private static void adicionarEstoquePredicados(Integer quantidadeEstoque, Root<ProdutoEntity> root,
                                                CriteriaBuilder criteriaBuilder, List<Predicate> pesquisa) {
        pesquisa.add(criteriaBuilder.equal(root.get("quantidadeEstoque"), quantidadeEstoque));
    }
}

