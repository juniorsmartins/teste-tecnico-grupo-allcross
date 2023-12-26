package br.com.desafiogrupoallcross.adapter.in.dto.filtro;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    private CategoriaFiltro categoria;

    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Formato de data inválido. Utilize o formato dd/MM/yyyy.")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String dataCadastro;
}

