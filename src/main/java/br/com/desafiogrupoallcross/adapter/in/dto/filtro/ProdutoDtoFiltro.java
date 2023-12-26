package br.com.desafiogrupoallcross.adapter.in.dto.filtro;

import br.com.desafiogrupoallcross.adapter.in.dto.request.CategoriaResumo;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public final class ProdutoDtoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String nome;

    private Boolean ativo;

    private BigDecimal valorCusto;

    private Double icms;

    private BigDecimal valorVenda;

    private Integer quantidadeEstoque;

    private CategoriaResumo categoria;

    public static ProdutoFiltro converterParaProdutoFiltro(ProdutoDtoFiltro dtoFiltro) {
        var produtoFiltro = new ProdutoFiltro();

        produtoFiltro.setId(dtoFiltro.getId());
        produtoFiltro.setNome(dtoFiltro.getNome());
        produtoFiltro.setAtivo(dtoFiltro.getAtivo());
        produtoFiltro.setValorCusto(dtoFiltro.getValorCusto());
        produtoFiltro.setIcms(dtoFiltro.getIcms());
        produtoFiltro.setValorVenda(dtoFiltro.getValorVenda());
        produtoFiltro.setQuantidadeEstoque(dtoFiltro.getQuantidadeEstoque());
        produtoFiltro.setCategoria(dtoFiltro.getCategoria());

        return produtoFiltro;
    }
}

