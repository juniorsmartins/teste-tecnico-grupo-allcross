package br.com.desafiogrupoallcross.utilitarios;

import br.com.desafiogrupoallcross.adapter.in.dto.request.CategoriaId;
import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoCadastrarDtoIn;
import br.com.desafiogrupoallcross.adapter.out.entity.CategoriaEntity;
import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.entity.enuns.TipoCategoriaEnum;
import br.com.desafiogrupoallcross.application.core.domain.Categoria;
import br.com.desafiogrupoallcross.application.core.domain.FotoProduto;
import br.com.desafiogrupoallcross.application.core.domain.ProdutoBusiness;
import com.github.javafaker.Faker;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

// Uso dos Design Patterns, Object Mother e Builder.
public final class FactoryObjectMotherAndBuilder {

    public static Faker faker = new Faker();

    public static Random random = new Random();

    public static ProdutoEntity.ProdutoEntityBuilder gerarProdutoEntityBuilder() {

        var categoria = CategoriaEntity.builder()
                .id(1L)
                .classe("Eletrônicos")
                .ativo(true)
                .tipo(TipoCategoriaEnum.NORMAL)
                .build();

        return ProdutoEntity.builder()
                .id(random.nextLong(200) + 100)
                .nome(faker.lorem().characters(10, 25))
                .ativo(random.nextBoolean())
                .sku(UUID.randomUUID())
                .valorCusto(BigDecimal.valueOf(10))
                .icms(10)
                .valorVenda(BigDecimal.valueOf(11))
                .quantidadeEstoque(random.nextInt(50) + 1)
                .categoria(categoria);
    }

    public static ProdutoBusiness gerarProdutoBusiness() {

        var categoria = new Categoria();
        categoria.setId(1L);
        categoria.setClasse("Eletrônicos");
        categoria.setAtivo(true);
        categoria.setTipo(TipoCategoriaEnum.NORMAL);

        var produto = new ProdutoBusiness();
        produto.setNome(faker.lorem().characters(5, 100));
        produto.setAtivo(random.nextBoolean());
        produto.setSku(UUID.randomUUID());
        produto.setValorCusto(BigDecimal.valueOf(10));
        produto.setIcms(10);
        produto.setValorVenda(BigDecimal.valueOf(11));
        produto.setDataCadastro(Instant.now().minusSeconds(2 * 365 * 24 * 60 * 60));
        produto.setQuantidadeEstoque(random.nextInt(50) + 1);
        produto.setCategoria(categoria);

        return produto;
    }

    public static ProdutoCadastrarDtoIn gerarProdutoCadastrarDtoIn() {
        var categoriaId = new CategoriaId(1L);

        var nome = faker.name().fullName();
        var ativo = random.nextBoolean();
        var valorCusto = BigDecimal.valueOf(10.0);
        var icms = 10.0;
        var valorVenda = BigDecimal.valueOf(11);
        var quantidadeEstoque = random.nextInt(50) + 1;

        return new ProdutoCadastrarDtoIn(nome, ativo, valorCusto, icms, valorVenda, quantidadeEstoque, categoriaId);
    }

    public static ProdutoCadastrarDtoIn.ProdutoCadastrarDtoInBuilder gerarProdutoCadastrarDtoInBuilder() {

        return ProdutoCadastrarDtoIn.builder()
                .nome("Rádio")
                .ativo(true)
                .valorCusto(BigDecimal.valueOf(500))
                .icms(30D)
                .valorVenda(BigDecimal.valueOf(650))
                .quantidadeEstoque(1)
                .categoria(new CategoriaId(1L));
    }

    public static MockMultipartFile gerarMockMultipartFile() throws IOException {

        // Cria um arquivo temporário com dados fictícios
        Path arquivoTemporario = Files.createTempFile("teste", ".jpg");
        Files.write(arquivoTemporario, "Teste file content".getBytes());

        // Cria um objeto MockMultipartFile a partir do arquivo temporário
        return new MockMultipartFile("file", "teste.jpg", "image/jpeg", Files.readAllBytes(arquivoTemporario));
    }

    public static FotoProduto gerarFotoProduto() throws IOException {

        MultipartFile multipartFile = gerarMockMultipartFile();

        var fotoProduto = new FotoProduto();
        fotoProduto.setFoto(multipartFile.getBytes());
        fotoProduto.setNome(multipartFile.getOriginalFilename());
        fotoProduto.setTamanho(multipartFile.getSize());
        fotoProduto.setTipo(multipartFile.getContentType());

        return fotoProduto;
    }

    public static FotoProdutoEntity.FotoProdutoEntityBuilder gerarFotoProdutoEntityBuilder() throws IOException {

        // Cria um arquivo temporário com dados fictícios
        Path arquivoTemporario = Files.createTempFile("teste", ".jpg");
        Files.write(arquivoTemporario, "Teste file content".getBytes());

        // Cria um objeto MockMultipartFile a partir do arquivo temporário
        MultipartFile multipartFile = new MockMultipartFile("file",
                "teste.jpg", "image/jpeg", Files.readAllBytes(arquivoTemporario));

        return FotoProdutoEntity.builder()
                .foto(multipartFile.getBytes())
                .nome(multipartFile.getOriginalFilename())
                .tipo(multipartFile.getContentType())
                .tamanho(multipartFile.getSize());
    }
}

