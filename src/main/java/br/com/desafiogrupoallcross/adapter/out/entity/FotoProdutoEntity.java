package br.com.desafiogrupoallcross.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.io.Serial;
import java.io.Serializable;

@Audited
@Entity
@Table(name = "fotos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public final class FotoProdutoEntity extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "tamanho")
    private long tamanho;

    public FotoProdutoEntity(Long id, String nome, String tipo, long tamanho, ProdutoEntity produto) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.produto = produto;
    }
}

