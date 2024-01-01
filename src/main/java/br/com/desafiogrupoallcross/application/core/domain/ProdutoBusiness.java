package br.com.desafiogrupoallcross.application.core.domain;

import br.com.desafiogrupoallcross.config.exception.http_400.CampoNuloProibidoException;
import br.com.desafiogrupoallcross.config.exception.http_400.CampoVazioProibidoException;
import br.com.desafiogrupoallcross.config.exception.http_400.DadoComTamanhoMaximoInvalidoException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class ProdutoBusiness {

    public static final int NOME_CARACTERES_MAXIMO = 100;

    private Long id;

    private UUID sku;

    private String nome;

    private boolean ativo;

    private BigDecimal valorCusto;

    private double icms;

    private BigDecimal valorVenda;

    private int quantidadeEstoque;

    private Categoria categoria;

    private Instant dataCadastro;

    private Instant dataAtualizacao;

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
        final String NOME = "Nome";

        Optional.ofNullable(nome)
            .ifPresentOrElse(name -> {
                this.checarCampoVazioOuEmBranco(NOME, name);
                this.checarTamanhoDeCampo(NOME, name, NOME_CARACTERES_MAXIMO);
                this.nome = name;
            },
            () -> {throw new CampoNuloProibidoException(NOME);}
        );
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public UUID getSku() {
        return sku;
    }

    public void setSku(UUID sku) {
        this.sku = sku;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        final String NOME_CAMPO = "valorCusto";

        Optional.ofNullable(valorCusto)
            .ifPresentOrElse(valor -> this.valorCusto = valor,
                () -> {throw new CampoNuloProibidoException(NOME_CAMPO);}
            );
    }

    public double getIcms() {
        return icms;
    }

    public void setIcms(double icms) {
        this.icms = icms;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        final String NOME_CAMPO = "valorVenda";

        Optional.ofNullable(valorVenda)
            .ifPresentOrElse(valor -> this.valorVenda = valor,
                () -> {throw new CampoNuloProibidoException(NOME_CAMPO);}
            );
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        final String NOME_CAMPO = "categoria";

        Optional.ofNullable(categoria)
            .ifPresentOrElse(classe -> this.categoria = categoria,
                () -> {throw new CampoNuloProibidoException(NOME_CAMPO);}
            );
    }

    private void checarCampoVazioOuEmBranco(String nomeCampo, String valorCampo) {
        if (valorCampo.isBlank()) {
            throw new CampoVazioProibidoException(nomeCampo);
        }
    }

    private void checarTamanhoDeCampo(String nomeCampo, String valorCampo, int tamanhoMaximno) {
        if (valorCampo.length() > tamanhoMaximno) {
            throw new DadoComTamanhoMaximoInvalidoException(nomeCampo, tamanhoMaximno, valorCampo.length());
        }
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Instant dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Instant dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoBusiness that = (ProdutoBusiness) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

