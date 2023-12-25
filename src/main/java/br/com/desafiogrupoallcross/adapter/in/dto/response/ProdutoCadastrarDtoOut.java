package br.com.desafiogrupoallcross.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@JsonPropertyOrder({
        "id", "sku", "nome", "categoria", "ativo", "valorCusto", "icms",
        "valorVenda", "quantidadeEstoque", "dataCadastro"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProdutoCadastrarDtoOut(

        Long id,

        UUID sku,

        String nome,

        CategoriaDtoOut categoria,

        boolean ativo,

        BigDecimal valorCusto,

        double icms,

        BigDecimal valorVenda,

        int quantidadeEstoque,

        Instant dataCadastro

) { }

