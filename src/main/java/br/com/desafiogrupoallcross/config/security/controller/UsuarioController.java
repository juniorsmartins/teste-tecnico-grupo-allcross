package br.com.desafiogrupoallcross.config.security.controller;

import br.com.desafiogrupoallcross.config.security.conversor.UsuarioCadastrarMapper;
import br.com.desafiogrupoallcross.config.security.dtoin.UsuarioCadastrarDtoIn;
import br.com.desafiogrupoallcross.config.exception.ApiError;
import br.com.desafiogrupoallcross.config.security.dtoout.UsuarioCadastrarDtoOut;
import br.com.desafiogrupoallcross.config.security.portas.in.UsuarioCadastrarInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Tag(name = "Usuários", description = "Contém todos os recursos de Usuário (cadastrar e consultar).")
@RestController
@RequestMapping(path = {"/api/v1/usuarios"})
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioCadastrarInputPort cadastrarInputPort;

    private final UsuarioCadastrarMapper cadastrarMapper;

    @PostMapping
    @Operation(summary = "Criar Usuário.", description = "Recurso para criar um novo Usuário.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioCadastrarDtoOut.class))),
            @ApiResponse(responseCode = "409", description = "Usuário com email já cadastrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))),
        })
    public ResponseEntity<UsuarioCadastrarDtoOut> create(@RequestBody @Valid UsuarioCadastrarDtoIn cadastrarDtoIn) {

        var resposta = Optional.ofNullable(cadastrarDtoIn)
                .map(this.cadastrarMapper::toUsuarioBusiness)
                .map(this.cadastrarInputPort::cadastrar)
                .map(this.cadastrarMapper::toUsuarioCadastrarDtoOut)
                .orElseThrow();

        return ResponseEntity
                .created(URI.create("/api/v1/usuarios/" + resposta.id()))
                .body(resposta);
    }
}

