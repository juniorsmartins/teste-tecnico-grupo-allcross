package br.com.desafiogrupoallcross.adapter.out.entity;

import br.com.desafiogrupoallcross.adapter.out.entity.enuns.TipoCategoriaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "categorias")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public final class CategoriaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoCategoriaEnum tipo;
}

