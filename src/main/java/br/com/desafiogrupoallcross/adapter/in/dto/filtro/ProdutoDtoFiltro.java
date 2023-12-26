package br.com.desafiogrupoallcross.adapter.in.dto.filtro;

import br.com.desafiogrupoallcross.adapter.in.dto.request.CategoriaId;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

    private CategoriaId categoria;

    public static ProdutoFiltro converterParaProdutoFiltro(ProdutoDtoFiltro dtoFiltro) {
        var produtoFiltro = new ProdutoFiltro();

        produtoFiltro.setId(dtoFiltro.getId());
        produtoFiltro.setNome(dtoFiltro.getNome());
        produtoFiltro.setAtivo(dtoFiltro.getAtivo());

        return produtoFiltro;
    }
}

