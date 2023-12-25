package br.com.desafiogrupoallcross.adapter.in.dto.filtro;

import br.com.desafiogrupoallcross.adapter.in.dto.request.CategoriaId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public final class ProdutoDtoFiltro implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private Boolean ativo;

    private BigDecimal valorCusto;

    private Double icms;

    private BigDecimal valorVenda;

    private Integer quantidadeEstoque;

    private CategoriaId categoria;
}

