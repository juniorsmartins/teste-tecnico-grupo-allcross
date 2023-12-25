package br.com.desafiogrupoallcross.adapter.in.dto.request;

import java.math.BigDecimal;

public record ProdutoCadastrarDtoIn(

        String nome,

        boolean ativo,

        BigDecimal valorCusto,

        double icms,

        BigDecimal valorVenda,

        int quantidadeEstoque,

        CategoriaId categoria

) { }

