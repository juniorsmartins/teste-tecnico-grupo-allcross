package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoCadastrarDtoOut;
import br.com.desafiogrupoallcross.application.port.in.AuditoriaProdutoInputPort;
import br.com.desafiogrupoallcross.config.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Auditoria", description = "Contém todos os recursos de Auditoria.")
@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class AuditoriaController {

    private final Logger log = LoggerFactory.getLogger(AuditoriaController.class);

    private final AuditoriaProdutoInputPort auditoriaProdutoInputPort;

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping(path = "/produtos/{produtoId}/auditoria",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Auditar Produtos", description = "Recurso para consultar auditoria de Produtos por Id.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida e com retorno.",
                content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 1, schema = @Schema(implementation = ProdutoCadastrarDtoOut.class), uniqueItems = true))}),
            @ApiResponse(responseCode = "400", description = "Requisição mal formulada.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar recurso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Situação inesperada no servidor.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<List<String>> consultarAuditoriaDeProdutoPorId(@PathVariable(name = "produtoId") final Long id) {

        log.info("Requisição recebida para consultar Auditoria de Produto com Id: {}.", id);

        var resposta = Optional.ofNullable(id)
                .map(this.auditoriaProdutoInputPort::consultarAuditoriaDeProdutoPorId)
                .orElseThrow();

        log.info("Auditoria consultada com sucesso para Produto com Id: {}.", id);

        return ResponseEntity
                .ok()
                .body(resposta);
    }
}

