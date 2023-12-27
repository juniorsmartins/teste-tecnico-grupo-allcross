package br.com.desafiogrupoallcross.application.core.domain.filtro;

import br.com.desafiogrupoallcross.adapter.in.dto.filtro.CategoriaFiltro;
import br.com.desafiogrupoallcross.adapter.in.dto.filtro.ProdutoDtoFiltro;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class ProdutoFiltro {

    private String id;

    private UUID sku;

    private String nome;

    private Boolean ativo;

    private BigDecimal valorCusto;

    private Double icms;

    private BigDecimal valorVenda;

    private Integer quantidadeEstoque;

    private CategoriaFiltro categoria;

    private String dataCadastro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getSku() {
        return sku;
    }

    public void setSku(UUID sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    public Double getIcms() {
        return icms;
    }

    public void setIcms(Double icms) {
        this.icms = icms;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public CategoriaFiltro getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaFiltro categoria) {
        this.categoria = categoria;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Instant dataCadastroInicial() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(this.dataCadastro, formatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 00, 00, 00);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

        return instant;
    }

    public Instant dataCadastroFinal() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(this.dataCadastro, formatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 23, 59, 59);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

        return instant;
    }

    public static ProdutoFiltro converterParaProdutoFiltro(ProdutoDtoFiltro dtoFiltro) {
        var produtoFiltro = new ProdutoFiltro();

        produtoFiltro.setId(dtoFiltro.getId());
        produtoFiltro.setSku(dtoFiltro.getSku());
        produtoFiltro.setNome(dtoFiltro.getNome());
        produtoFiltro.setAtivo(dtoFiltro.getAtivo());
        produtoFiltro.setValorCusto(dtoFiltro.getValorCusto());
        produtoFiltro.setIcms(dtoFiltro.getIcms());
        produtoFiltro.setValorVenda(dtoFiltro.getValorVenda());
        produtoFiltro.setQuantidadeEstoque(dtoFiltro.getQuantidadeEstoque());
        produtoFiltro.setCategoria(dtoFiltro.getCategoria());
        produtoFiltro.setDataCadastro(dtoFiltro.getDataCadastro());

        return produtoFiltro;
    }
}

