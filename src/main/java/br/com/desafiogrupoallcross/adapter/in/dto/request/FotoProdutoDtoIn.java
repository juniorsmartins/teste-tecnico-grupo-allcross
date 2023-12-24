package br.com.desafiogrupoallcross.adapter.in.dto.request;

import br.com.desafiogrupoallcross.adapter.in.anotacoes.FileContentType;
import br.com.desafiogrupoallcross.adapter.in.anotacoes.FileSize;
import lombok.Builder;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record FotoProdutoDtoIn(

    @FileSize(max = "2MB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    MultipartFile foto,

    String descricao

) { }

