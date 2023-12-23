package br.com.desafiogrupoallcross.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class ProdutoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "sku")
    private UUID sku;

    @Column(name = "valor_custo")
    private BigDecimal valorCusto;

//    private String categoria; // tornar entidade

    @Column(name = "icms")
    private double icms;

    @Column(name = "valor_venda")
    private BigDecimal valorVenda;

//    private byte[] imagem;

    @Column(name = "data_cadastro")
    private Instant dataCadastro;

    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;
}

