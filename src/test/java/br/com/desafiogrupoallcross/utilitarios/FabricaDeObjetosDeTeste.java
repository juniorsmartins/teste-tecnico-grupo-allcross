package br.com.desafiogrupoallcross.utilitarios;

import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

// Uso dos Design Patterns, Object Mother e Builder.
public final class FabricaDeObjetosDeTeste {

    public static Faker faker = new Faker();

    public static Random random = new Random();

    public static ProdutoEntity.ProdutoEntityBuilder gerarProdutoEntityBuilder() {

        return ProdutoEntity.builder()
                .id(random.nextLong(200) + 100)
                .nome(faker.name().fullName())
                .ativo(random.nextBoolean())
                .sku(UUID.randomUUID())
                .valorCusto(BigDecimal.valueOf(10))
                .icms(10)
                .valorVenda(BigDecimal.valueOf(11))
                .dataCadastro(Instant.now().minusSeconds(2 * 365 * 24 * 60 * 60))
                .quantidadeEstoque(random.nextInt(50) + 1);
    }

    public static ProdutoBusiness gerarProdutoBusiness() {

        var produto = new ProdutoBusiness();
        produto.setNome(faker.name().fullName());
        produto.setAtivo(random.nextBoolean());
        produto.setSku(UUID.randomUUID());
        produto.setValorCusto(BigDecimal.valueOf(10));
        produto.setIcms(10);
        produto.setValorVenda(BigDecimal.valueOf(11));
        produto.setDataCadastro(Instant.now().minusSeconds(2 * 365 * 24 * 60 * 60));
        produto.setQuantidadeEstoque(random.nextInt(50) + 1);

        return produto;
    }
}

