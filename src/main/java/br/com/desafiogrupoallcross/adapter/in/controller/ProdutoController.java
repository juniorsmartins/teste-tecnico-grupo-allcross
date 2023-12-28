package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.in.dto.filtro.ProdutoDtoFiltro;
import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoAtualizarDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoCadastrarDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoAgregadoDtoOut;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoCadastrarDtoOut;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoPesquisarDtoOut;
import br.com.desafiogrupoallcross.adapter.in.mapper.ProdutoAgregadoMapper;
import br.com.desafiogrupoallcross.adapter.in.mapper.ProdutoMapper;
import br.com.desafiogrupoallcross.application.core.domain.filtro.ProdutoFiltro;
import br.com.desafiogrupoallcross.application.port.in.*;
import br.com.desafiogrupoallcross.config.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Tag(name = "Produtos", description = "Contém todos os recursos de Produto (cadastrar, consultar, atualizar, ativar/desativar e deletar).")
@RestController
@RequestMapping(path = "/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoCadastrarInputPort inputPort;

    private final ProdutoPesquisarInputPort pesquisarInputPort;

    private final ProdutoInverterStatusAtivoInputPort ativoInputPort;

    private final ProdutoDeletarInputPort deletarInputPort;

    private final ProdutoAtualizarInputPort atualizarInputPort;

    private final ProdutoListarAgregadosInputPort listarAgregadosInputPort;

    private final ProdutoMapper mapper;

    private final ProdutoAgregadoMapper agregadoMapper;

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Cadastrar Produto", description = "Recurso para cadastrar um novo Produto.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Recurso cadastrado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoCadastrarDtoOut.class))),
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
    public ResponseEntity<ProdutoCadastrarDtoOut> cadastrar(
            @Parameter(name = "ProdutoCadastrarDtoIn", description = "Objeto para transporte de dados de entrada.", required = true)
            @RequestBody @Valid ProdutoCadastrarDtoIn dtoIn) {

        var resposta = Optional.ofNullable(dtoIn)
                .map(mapper::toProdutoBusiness)
                .map(inputPort::cadastrar)
                .map(mapper::toProdutoCadastrarDtoOut)
                .orElseThrow();

        return ResponseEntity
                .created(URI.create("/api/v1/produtos/" + resposta.id()))
                .body(resposta);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Pesquisar Produtos", description = "Recurso para pesquisar Produtos.",
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
    public ResponseEntity<Page<ProdutoPesquisarDtoOut>> pesquisar(
        @Parameter(name = "ProdutoDtoFiltro", description = "Objeto para transporte de dados usados como filtros de pesquisa.", required = false)
        @Valid final ProdutoDtoFiltro produtoDtoFiltro,
        @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10) final Pageable paginacao) {

        var paginaDtoOut = Optional.ofNullable(produtoDtoFiltro)
                .map(ProdutoFiltro::converterParaProdutoFiltro)
                .map(filtro -> this.pesquisarInputPort.pesquisar(filtro, paginacao))
                .map(pagina -> pagina.map(this.mapper::toProdutoPesquisarDtoOut))
                .orElseThrow();

        return ResponseEntity
                .ok()
                .body(paginaDtoOut);
    }

    @PatchMapping(path = {"/{produtoId}/inverter-status-ativo"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Inverter Status Ativo do Produto", description = "Recurso para modificar o valor do atributo ativo do Produto.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida e com retorno.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoCadastrarDtoOut.class))),
            @ApiResponse(responseCode = "400", description = "Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<ProdutoCadastrarDtoOut> inverterStatusAtivo(
            @Parameter(name = "id", description = "Chave de Identificação.", example = "78", required = true)
            @PathVariable(name = "produtoId") final Long id) {

        var resposta = Optional.ofNullable(id)
                .map(this.ativoInputPort::inverterStatusAtivo)
                .map(this.mapper::toProdutoCadastrarDtoOut)
                .orElseThrow(NoSuchElementException::new);

        return ResponseEntity
                .ok()
                .body(resposta);
    }

    @DeleteMapping(path = {"/{produtoId}"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Deletar Produto", description = "Recurso para apagar Produto.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Requisição bem sucedida e sem retorno.",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<Void> deletePorId(
            @Parameter(name = "id", description = "Chave de Identificação.", example = "78", required = true)
            @PathVariable(name = "produtoId") final Long id) {

        Optional.ofNullable(id)
            .ifPresentOrElse(this.deletarInputPort::deletarPorId,
                () -> {throw new NoSuchElementException();}
            );

        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Pesquisar Produtos", description = "Recurso para pesquisar Produtos.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida e com retorno.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoCadastrarDtoOut.class))),
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
    public ResponseEntity<ProdutoCadastrarDtoOut> atualizar(
            @Parameter(name = "ProdutoAtualizarDtoIn", description = "Objeto para transporte de dados para atualizar.", required = true)
            @RequestBody @Valid ProdutoAtualizarDtoIn atualizarDtoIn) {

        var resposta = Optional.ofNullable(atualizarDtoIn)
                .map(this.mapper::toProdutoBusiness)
                .map(this.atualizarInputPort::atualizar)
                .map(this.mapper::toProdutoCadastrarDtoOut)
                .orElseThrow();

        return ResponseEntity
                .ok()
                .body(resposta);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Pesquisar Produtos", description = "Recurso para pesquisar Produtos.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida e com retorno.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoCadastrarDtoOut.class))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<List<ProdutoAgregadoDtoOut>> listarAgregados() {

        var resposta = this.listarAgregadosInputPort.listarAgregados()
                .stream()
                .map(this.agregadoMapper::toProdutoAgregadoDtoOut)
                .toList();

        return ResponseEntity
                .ok()
                .body(resposta);
    }
}

