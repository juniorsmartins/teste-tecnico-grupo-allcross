package br.com.desafiogrupoallcross.application.core.domain;

import br.com.desafiogrupoallcross.adapter.out.entity.enuns.TipoCategoriaEnum;

public final class CategoriaBusiness {

    private Long id;

    private String nome;

    private boolean ativo;

    private TipoCategoriaEnum tipo;

    public CategoriaBusiness() {}

    public CategoriaBusiness(Long id, String nome, boolean ativo, TipoCategoriaEnum tipo) {
        this.setId(id);
        this.setNome(nome);
        this.setAtivo(ativo);
        this.setTipo(tipo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public TipoCategoriaEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoCategoriaEnum tipo) {
        this.tipo = tipo;
    }
}

