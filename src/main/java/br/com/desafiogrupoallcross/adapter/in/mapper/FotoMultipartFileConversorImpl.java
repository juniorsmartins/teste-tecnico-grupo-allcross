package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.out.utilitario.FotoUtils;
import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FotoMultipartFileConversorImpl implements FotoMultipartFileConversor {

    @Override
    public FotoProduto paraFotoProduto(MultipartFile foto) {

        var fotoProduto = new FotoProduto();
        fotoProduto.setNome(foto.getOriginalFilename());
        fotoProduto.setTipo(foto.getContentType());
        fotoProduto.setTamanho(foto.getSize());

        var fotoConvertidaEmByte = FotoUtils.converterMultipartFileEmArrayDeByte(foto);
        fotoProduto.setFoto(fotoConvertidaEmByte);

        return fotoProduto;
    }
}

