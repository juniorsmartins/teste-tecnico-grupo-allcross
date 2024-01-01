package br.com.desafiogrupoallcross.application.core.domain;

import br.com.desafiogrupoallcross.adapter.out.entity.enuns.TipoCategoriaEnum;

public final class Categoria {

    private Long id;

    private String classe;

    private boolean ativo;

    private TipoCategoriaEnum tipo;

    public Categoria() {}

    public Categoria(Long id, String classe, boolean ativo, TipoCategoriaEnum tipo) {
        this.setId(id);
        this.setClasse(classe);
        this.setAtivo(ativo);
        this.setTipo(tipo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
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

