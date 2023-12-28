package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.application.port.in.AuditoriaProdutoInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class AuditoriaController {

    private final AuditoriaProdutoInputPort auditoriaProdutoInputPort;

    @GetMapping(path = "/produtos/auditoria/{id}")
    public ResponseEntity<List<String>> consultarAuditoriaDeProdutoPorId(@PathVariable(name = "id") final Long id) {

        var resposta = Optional.ofNullable(id)
                .map(this.auditoriaProdutoInputPort::consultarAuditoriaDeProdutoPorId)
                .orElseThrow();

        return ResponseEntity
                .ok()
                .body(resposta);
    }
}

