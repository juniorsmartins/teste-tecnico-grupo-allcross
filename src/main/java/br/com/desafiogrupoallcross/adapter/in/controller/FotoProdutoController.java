package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.in.dto.request.FotoProdutoDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.response.FotoProdutoListarDtoOut;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoCadastrarDtoOut;
import br.com.desafiogrupoallcross.adapter.in.mapper.FotoMultipartFileConversor;
import br.com.desafiogrupoallcross.adapter.in.mapper.FotoProdutoMapper;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoArmazenarInputPort;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoCadastrarInputPort;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoListarInputPort;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoRecuperarInputPort;
import br.com.desafiogrupoallcross.config.exception.ApiError;
import br.com.desafiogrupoallcross.config.exception.http_400.FotoProdutoCadastrarControllerException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Tag(name = "FotosProduto", description = "Contém todos os recursos de FotoProduto (cadastrar).")
@RestController
@RequestMapping(path = "/api/v1/produtos")
@RequiredArgsConstructor
public class FotoProdutoController {

    private final Logger log = LoggerFactory.getLogger(FotoProdutoController.class);

    private final FotoProdutoCadastrarInputPort inputPort;

    private final FotoProdutoMapper mapper;

    private final FotoProdutoArmazenarInputPort armazenarInputPort;

    private final FotoProdutoRecuperarInputPort recuperarInputPort;

    private final FotoProdutoListarInputPort fotoProdutoListarInputPort;

    private final FotoMultipartFileConversor fotoMultipartFileConversor;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ESTOQUISTA')")
    @PostMapping(path = {"/{produtoId}/imagem"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Cadastrar FotoProduto", description = "Recurso para cadastrar uma nova FotoProduto. A requisição exige Bearer Token. Acesso restrito para ADMINISTRADOR|ESTOQUISTA.",
        security = {@SecurityRequirement(name = "security")},
        responses = {
            @ApiResponse(responseCode = "204", description = "Requisição bem sucedida e sem retorno.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com regras de negócio.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<Void> cadastrarImagem(
            @Parameter(name = "id", description = "Chave de Identificação.", example = "78", required = true)
            @PathVariable(name = "produtoId") final Long id,
            @Parameter(name = "FotoProdutoDtoIn", description = "Objeto para transporte de dados para cadastrar.", required = true)
            @Valid FotoProdutoDtoIn dtoIn) {

        log.info("Requisição recebida para cadastrar imagem de Produto com Id: {}.", id);

        Optional.ofNullable(dtoIn)
                .map(mapper::toFotoProdutoBusiness)
                .map(foto -> {
                    inputPort.cadastrarImagem(id, foto);
                    return true;
                })
                .orElseThrow(FotoProdutoCadastrarControllerException::new);

        log.info("Imagem cadastrada com sucesso para Produto com Id: {}.", id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ESTOQUISTA')")
    @PostMapping(path = {"/fotos"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Cadastrar FotoProduto", description = "Recurso para cadastrar uma nova FotoProduto. A requisição exige Bearer Token. Acesso restrito para ADMINISTRADOR|ESTOQUISTA.",
        security = {@SecurityRequirement(name = "security")},
        responses = {
            @ApiResponse(responseCode = "204", description = "Requisição bem sucedida e sem retorno.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com regras de negócio.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<Void> armazenar(
        @Parameter(name = "produtoId", description = "Chave de Identificação do Produto.", example = "78", required = true)
        @RequestParam(name = "produtoId") final Long id,
        @Parameter(name = "MultipartFile", description = "MultipartFile é um objeto que representa um arquivo que será serializado no corpo de uma requisição.", required = true)
        @RequestParam("foto") MultipartFile file) {

        log.info("Requisição recebida para cadastrar imagem de Produto com Id: {}.", id);

        Objects.requireNonNull(id);
        Objects.requireNonNull(file);

        var fileConvertidoEmFotoProduto = this.fotoMultipartFileConversor.paraFotoProduto(file);
        this.armazenarInputPort.armazenar(id, fileConvertidoEmFotoProduto);

        log.info("Imagem cadastrada com sucesso para Produto com Id: {}.", id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ESTOQUISTA')")
    @GetMapping(path = {"/{produtoId}/foto"})
    @Operation(summary = "Consultar Foto", description = "Recurso para consultar Foto por Id de Produto. A requisição exige Bearer Token. Acesso restrito para ADMINISTRADOR|ESTOQUISTA.",
        security = {@SecurityRequirement(name = "security")},
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida e com retorno.",
                content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 1, schema = @Schema(implementation = ProdutoCadastrarDtoOut.class), uniqueItems = true))}),
            @ApiResponse(responseCode = "400", description = "Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com regras de negócio.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<Stream<byte[]>> recuperarFoto(@PathVariable(name = "produtoId") final Long id) {

        log.info("");

        var resposta = Optional.ofNullable(id)
                .map(this.recuperarInputPort::recuperarFoto)
                .orElseThrow();

        log.info("");

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf("image/*"))
                .body(resposta);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ESTOQUISTA')")
    @GetMapping(path = {"/fotos"})
    @Operation(summary = "Listar Fotos", description = "Recurso para listar Fotos de Produto. A requisição exige Bearer Token. Acesso restrito para ADMINISTRADOR|ESTOQUISTA.",
        security = {@SecurityRequirement(name = "security")},
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida e com retorno.",
                content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 1, schema = @Schema(implementation = ProdutoCadastrarDtoOut.class), uniqueItems = true))}),
            @ApiResponse(responseCode = "400", description = "Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Conflito com regras de negócio.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<List<FotoProdutoListarDtoOut>> listar() {

        log.info("");

        var resposta = this.fotoProdutoListarInputPort.listar()
                .stream()
                .map(this.mapper::toFotoProdutoListarDtoOut)
                .toList();

        log.info("");

        return ResponseEntity
            .ok()
            .body(resposta);
    }
}

