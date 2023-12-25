package br.com.desafiogrupoallcross.utilitarios;

import br.com.desafiogrupoallcross.adapter.in.dto.request.CategoriaId;
import br.com.desafiogrupoallcross.adapter.in.dto.request.FotoProdutoDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoCadastrarDtoIn;
import br.com.desafiogrupoallcross.adapter.out.entity.FotoProdutoEntity;
import br.com.desafiogrupoallcross.adapter.out.entity.ProdutoEntity;
import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
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
        produto.setNome(faker.lorem().characters(5, 100));
        produto.setAtivo(random.nextBoolean());
        produto.setSku(UUID.randomUUID());
        produto.setValorCusto(BigDecimal.valueOf(10));
        produto.setIcms(10);
        produto.setValorVenda(BigDecimal.valueOf(11));
        produto.setDataCadastro(Instant.now().minusSeconds(2 * 365 * 24 * 60 * 60));
        produto.setQuantidadeEstoque(random.nextInt(50) + 1);

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

    public static FotoProdutoDtoIn.FotoProdutoDtoInBuilder gerarFotoProdutoDtoInBuilder() throws IOException {

        // Cria um arquivo temporário com dados fictícios
        Path arquivoTemporario = Files.createTempFile("teste", ".jpg");
        Files.write(arquivoTemporario, "Teste file content".getBytes());

        // Cria um objeto MockMultipartFile a partir do arquivo temporário
        MultipartFile multipartFile = new MockMultipartFile("file",
                "teste.jpg", "image/jpeg", Files.readAllBytes(arquivoTemporario));

        return FotoProdutoDtoIn.builder()
                .foto(multipartFile)
                .descricao("descrição X");
    }

    public static FotoProdutoBusiness gerarFotoProdutoBusinessSemFoto() throws IOException {

        // Cria um arquivo temporário com dados fictícios
        Path arquivoTemporario = Files.createTempFile("teste", ".jpg");
        Files.write(arquivoTemporario, "Teste file content".getBytes());

        // Cria um objeto MockMultipartFile a partir do arquivo temporário
        MultipartFile multipartFile = new MockMultipartFile("file",
                "teste.jpg", "image/jpeg", Files.readAllBytes(arquivoTemporario));

        var fotoProdutoBusiness = new FotoProdutoBusiness();
        fotoProdutoBusiness.setNome(multipartFile.getOriginalFilename());
        fotoProdutoBusiness.setTamanho(multipartFile.getSize());
        fotoProdutoBusiness.setTipo(multipartFile.getContentType());
        fotoProdutoBusiness.setDescricao("Descrição X");

        return fotoProdutoBusiness;
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
                .descricao("Descrição X")
                .tipo(multipartFile.getContentType())
                .tamanho(multipartFile.getSize());
    }
}

