package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.out.utilitario.FotoUtils;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoRecuperar;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FotoMultipartFileEncapsulateImpl implements FotoMultipartFileEncapsulate {

    @Override
    public FotoProdutoRecuperar encapsularFoto(MultipartFile foto) {

        var fotoConvertidaEmByte = FotoUtils.converterMultipartFileEmArrayDeByte(foto);
        var fotoCompactadaEmByte = FotoUtils.compactarFoto(fotoConvertidaEmByte);

        var fotoRecuperar = new FotoProdutoRecuperar();
        fotoRecuperar.setFoto(fotoCompactadaEmByte);
        fotoRecuperar.setNome(foto.getOriginalFilename());
        fotoRecuperar.setTipo(foto.getContentType());
        fotoRecuperar.setTamanho(foto.getSize());

        return fotoRecuperar;
    }
}

