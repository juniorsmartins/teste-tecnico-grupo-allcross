package br.com.desafiogrupoallcross.adapter.in.dto.response;

import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoId;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FotoProdutoListarDtoOut(

    Long id,

    String nome,

    String tipo,

    long tamanho,

    ProdutoId produto

) { }

