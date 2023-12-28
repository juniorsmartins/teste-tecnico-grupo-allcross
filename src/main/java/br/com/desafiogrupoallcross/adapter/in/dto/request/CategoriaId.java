package br.com.desafiogrupoallcross.adapter.in.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CategoriaId(

    @NotNull
    @Positive
    Long id

) { }

