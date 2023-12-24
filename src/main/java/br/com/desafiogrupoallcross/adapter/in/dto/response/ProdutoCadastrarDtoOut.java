package br.com.desafiogrupoallcross.adapter.in.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProdutoCadastrarDtoOut(

        Long id,

        UUID sku,

        String nome,

        boolean ativo,

        BigDecimal valorCusto,

//    private String categoria; // tornar entidade

        double icms,

        BigDecimal valorVenda,

//    private byte[] imagem;

        Instant dataCadastro,

        int quantidadeEstoque

) { }

