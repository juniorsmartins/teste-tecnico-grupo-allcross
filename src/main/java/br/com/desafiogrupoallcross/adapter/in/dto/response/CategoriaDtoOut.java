package br.com.desafiogrupoallcross.adapter.in.dto.response;

import br.com.desafiogrupoallcross.adapter.out.entity.enuns.TipoCategoriaEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoriaDtoOut(

    Long id,

    String classe,

    boolean ativo,

    TipoCategoriaEnum tipo

) { }

