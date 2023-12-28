package br.com.desafiogrupoallcross.adapter.in.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProdutoCadastrarDtoIn(

        @NotBlank
        @Size(max = 100)
        String nome,

        boolean ativo,

        @NotNull
        @Positive
        BigDecimal valorCusto,

        @PositiveOrZero
        double icms,

        @NotNull
        @Positive
        BigDecimal valorVenda,

        @PositiveOrZero
        int quantidadeEstoque,

        @NotNull
        @Valid
        CategoriaId categoria

) { }

