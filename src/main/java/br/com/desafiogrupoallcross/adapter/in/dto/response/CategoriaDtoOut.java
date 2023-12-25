package br.com.desafiogrupoallcross.adapter.in.dto.response;

import br.com.desafiogrupoallcross.adapter.out.entity.enuns.TipoCategoriaEnum;

public record CategoriaDtoOut(

    Long id,

    String nome,

    boolean ativo,

    TipoCategoriaEnum tipo

) { }

