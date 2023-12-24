package br.com.desafiogrupoallcross.application.core.domain;

import org.springframework.web.multipart.MultipartFile;

public class FotoProdutoBusiness {

    private MultipartFile foto;

    private String descricao;

    public MultipartFile getFoto() {
        return foto;
    }

    public void setFoto(MultipartFile foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

