package br.com.desafiogrupoallcross.adapter.in.dto.request;

import java.math.BigDecimal;

public record ProdutoCadastrarDtoIn(

        String nome,

        boolean ativo,

        BigDecimal valorCusto,

//    private String categoria; // tornar entidade

        double icms,

        BigDecimal valorVenda,

//    private byte[] imagem;

        int quantidadeEstoque

) { }

