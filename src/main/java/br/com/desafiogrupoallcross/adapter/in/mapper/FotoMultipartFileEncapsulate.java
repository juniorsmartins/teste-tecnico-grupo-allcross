package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoRecuperar;
import org.springframework.web.multipart.MultipartFile;

public interface FotoMultipartFileEncapsulate {

    FotoProdutoRecuperar encapsularFoto(MultipartFile foto);
}

