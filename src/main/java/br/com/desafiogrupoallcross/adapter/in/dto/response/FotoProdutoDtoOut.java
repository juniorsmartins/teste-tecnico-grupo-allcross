package br.com.desafiogrupoallcross.adapter.in.dto.response;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record FotoProdutoDtoOut(

    MultipartFile foto,

    String descricao

) { }

