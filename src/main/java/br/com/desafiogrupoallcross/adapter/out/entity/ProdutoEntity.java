package br.com.desafiogrupoallcross.adapter.out.entity;

import br.com.desafiogrupoallcross.application.core.domain.Categoria;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.envers.Audited;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Audited
@Entity
@Table(name = "produtos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public final class ProdutoEntity extends AbstractAuditingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Generated(GenerationTime.ALWAYS)
    @Column(name = "sku", nullable = false, columnDefinition = "uuid DEFAULT gen_random_uuid()", updatable = false)
    private UUID sku;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "valor_custo", nullable = false, scale = 2)
    private BigDecimal valorCusto;

    @Column(name = "icms")
    private double icms;

    @Column(name = "valor_venda", nullable = false, scale = 2)
    private BigDecimal valorVenda;

    @OneToMany(mappedBy = "produto")
    private List<FotoProdutoEntity> fotos;

    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

    public static ProdutoEntity converterParaEntity(ProdutoBusiness produtoBusiness) {
        var entidade = new ProdutoEntity();
        entidade.setId(produtoBusiness.getId());
        entidade.setSku(produtoBusiness.getSku());
        entidade.setNome(produtoBusiness.getNome());
        entidade.setAtivo(produtoBusiness.isAtivo());
        entidade.setValorCusto(produtoBusiness.getValorCusto());
        entidade.setIcms(produtoBusiness.getIcms());
        entidade.setValorVenda(produtoBusiness.getValorVenda());
        entidade.setQuantidadeEstoque(produtoBusiness.getQuantidadeEstoque());
        entidade.setCategoria(new CategoriaEntity(
                produtoBusiness.getCategoria().getId(),
                produtoBusiness.getCategoria().getClasse(),
                produtoBusiness.getCategoria().isAtivo(),
                produtoBusiness.getCategoria().getTipo()
            ));
        entidade.setDataCadastro(produtoBusiness.getDataCadastro());
        entidade.setDataAtualizacao(produtoBusiness.getDataAtualizacao());

        return entidade;
    }

    public static ProdutoBusiness converterParaBusiness(ProdutoEntity produtoEntity) {
        var business = new ProdutoBusiness();
        business.setId(produtoEntity.getId());
        business.setSku(produtoEntity.getSku());
        business.setNome(produtoEntity.getNome());
        business.setAtivo(produtoEntity.isAtivo());
        business.setValorCusto(produtoEntity.getValorCusto());
        business.setIcms(produtoEntity.getIcms());
        business.setValorVenda(produtoEntity.getValorVenda());
        business.setQuantidadeEstoque(produtoEntity.getQuantidadeEstoque());
        business.setCategoria(new Categoria(
                produtoEntity.getCategoria().getId(),
                produtoEntity.getCategoria().getClasse(),
                produtoEntity.getCategoria().isAtivo(),
                produtoEntity.getCategoria().getTipo()
        ));
        business.setDataCadastro(produtoEntity.getDataCadastro());
        business.setDataAtualizacao(produtoEntity.getDataAtualizacao());

        return business;
    }
}

