package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.adapter.out.utilitario.FotoUtils;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoRecuperar;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FotoMultipartFileEncapsulateImpl implements FotoMultipartFileEncapsulate {

    @Override
    public FotoProdutoRecuperar encapsularFoto(MultipartFile foto) {

        var fotoRecuperar = new FotoProdutoRecuperar();
        fotoRecuperar.setNome(foto.getOriginalFilename());
        fotoRecuperar.setTipo(foto.getContentType());
        fotoRecuperar.setTamanho(foto.getSize());

        var fotoConvertidaEmByte = FotoUtils.converterMultipartFileEmArrayDeByte(foto);
        var fotoCompactadaEmByte = FotoUtils.compactarFoto(fotoConvertidaEmByte);
        fotoRecuperar.setFoto(fotoCompactadaEmByte);

        return fotoRecuperar;
    }
}

