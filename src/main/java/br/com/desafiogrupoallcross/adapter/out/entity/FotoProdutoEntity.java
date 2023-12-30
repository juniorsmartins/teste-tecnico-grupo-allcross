package br.com.desafiogrupoallcross.adapter.out.entity;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBuscar;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "tamanho")
    private long tamanho;

    public static FotoProdutoBuscar converterEntityParaBusiness(FotoProdutoEntity entity) {
        InputStream fotoStream = new ByteArrayInputStream(entity.getFoto());

        var produtoBuscar = new FotoProdutoBuscar();
        produtoBuscar.setFoto(fotoStream);
        produtoBuscar.setNome(entity.getNome());
        produtoBuscar.setDescricao(entity.getDescricao());
        produtoBuscar.setTipo(entity.getTipo());
        produtoBuscar.setTamanho(entity.getTamanho());

        return produtoBuscar;
    }
}

