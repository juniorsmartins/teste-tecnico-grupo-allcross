package br.com.desafiogrupoallcross.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({
        "classe", "valorCusto", "icms", "valorVenda", "quantidadeEstoque", "custoTotal", "valorTotal"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProdutoAgregadoDtoOut(

    String nome,

    BigDecimal valorCusto,

    double icms,

    BigDecimal valorVenda,

    int quantidadeEstoque,

    BigDecimal custoTotal,

    BigDecimal valorTotal

) { }

