package br.com.desafiogrupoallcross.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "fotos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"produto"})
public final class FotoProdutoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    private byte[] foto;

    private String descricao;
}
