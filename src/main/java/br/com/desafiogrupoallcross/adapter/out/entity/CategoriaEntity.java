package br.com.desafiogrupoallcross.adapter.out.entity;

import br.com.desafiogrupoallcross.adapter.out.entity.enuns.TipoCategoriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.io.Serial;
import java.io.Serializable;

@Audited
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

    @Column(name = "classe", nullable = false)
    private String classe;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoCategoriaEnum tipo;
}

