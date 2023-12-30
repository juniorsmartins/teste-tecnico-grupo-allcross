package br.com.desafiogrupoallcross.application.core.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public final class FotoProdutoBuscar {

    private InputStream foto;

    private String nome;

    private String descricao;

    private String tipo;

    private long tamanho;

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getTamanho() {
        return tamanho;
    }

    public void setTamanho(long tamanho) {
        this.tamanho = tamanho;
    }
}

