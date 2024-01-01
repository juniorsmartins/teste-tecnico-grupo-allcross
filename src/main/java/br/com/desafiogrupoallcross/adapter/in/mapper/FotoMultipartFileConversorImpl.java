package br.com.desafiogrupoallcross.adapter.in.mapper;

import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import br.com.desafiogrupoallcross.config.exception.http_404.MultipartFileNaoEncontradoException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FotoMultipartFileConversorImpl implements FotoMultipartFileConversor {

    @Override
    public FotoProduto paraFotoProduto(MultipartFile foto) {

        var fotoProduto = new FotoProduto();
        fotoProduto.setNome(foto.getOriginalFilename());
        fotoProduto.setTipo(foto.getContentType());
        fotoProduto.setTamanho(foto.getSize());

        var fotoConvertidaEmByte = this.converterMultipartFileEmArrayDeByte(foto);
        fotoProduto.setFoto(fotoConvertidaEmByte);

        return fotoProduto;
    }

    public byte[] converterMultipartFileEmArrayDeByte(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new MultipartFileNaoEncontradoException();
        }
    }
}

