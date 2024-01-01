package br.com.desafiogrupoallcross.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@JsonPropertyOrder({
        "id", "sku", "classe", "ativo", "valorCusto", "icms",
        "valorVenda", "quantidadeEstoque", "categoria", "dataCadastro", "dataAtualizacao"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProdutoCadastrarDtoOut(

        Long id,

        UUID sku,

        String nome,

        boolean ativo,

        BigDecimal valorCusto,

        double icms,

        BigDecimal valorVenda,

        int quantidadeEstoque,

        CategoriaDtoOut categoria,

        Instant dataCadastro,

        Instant dataAtualizacao

) { }

