package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import org.springframework.web.multipart.MultipartFile;

public interface FotoMultipartFileConversor {

    FotoProduto paraFotoProduto(MultipartFile foto);
}

