package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoCadastrarDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoCadastrarDtoOut;
import br.com.desafiogrupoallcross.adapter.in.mapper.ProdutoCadastrarMapper;
import br.com.desafiogrupoallcross.application.port.in.ProdutoCadastrarInputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.ProdutoCadastrarControllerException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoCadastrarInputPort inputPort;

    private final ProdutoCadastrarMapper mapper;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<ProdutoCadastrarDtoOut> cadastrar(@RequestBody @Valid ProdutoCadastrarDtoIn dtoIn) {

        var resposta = Optional.ofNullable(dtoIn)
                .map(mapper::toProdutoBusiness)
                .map(inputPort::cadastrar)
                .map(mapper::toProdutoCadastrarDtoOut)
                .orElseThrow(ProdutoCadastrarControllerException::new);

        return ResponseEntity
                .created(URI.create("/api/v1/produtos/" + resposta.id()))
                .body(resposta);
    }
}

