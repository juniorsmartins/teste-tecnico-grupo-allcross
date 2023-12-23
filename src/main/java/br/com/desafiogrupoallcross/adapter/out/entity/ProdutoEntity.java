package br.com.desafiogrupoallcross.adapter.out.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Generated;

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

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "sku", nullable = false, columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID sku;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "valor_custo", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorCusto;

//    private String categoria; // tornar entidade

    @Column(name = "icms")
    private double icms;

    @Column(name = "valor_venda", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorVenda;

//    private byte[] imagem;

    @Column(name = "data_cadastro", nullable = false, insertable = true, updatable = false)
    private Instant dataCadastro;

    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;

    @PrePersist
    private void acionarAntesDePersistir() {
        this.dataCadastro = Instant.now();
    }
}

