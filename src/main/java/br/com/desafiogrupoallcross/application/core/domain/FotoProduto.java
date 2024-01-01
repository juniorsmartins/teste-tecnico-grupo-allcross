package br.com.desafiogrupoallcross.application.core.domain;

import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoId;

public final class FotoProduto {

    private Long id;

    private byte[] foto;

    private String nome;

    private String tipo;

    private long tamanho;

    private ProdutoId produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public ProdutoId getProduto() {
        return produto;
    }

    public void setProduto(ProdutoId produto) {
        this.produto = produto;
    }
}

