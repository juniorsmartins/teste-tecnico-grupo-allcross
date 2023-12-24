package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.in.dto.request.FotoProdutoDtoIn;
import br.com.desafiogrupoallcross.adapter.in.mapper.FotoProdutoCadastrarMapper;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoCadastrarInputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.FotoProdutoCadastrarControllerException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/produtos")
@RequiredArgsConstructor
public class FotoProdutoController {

    private final FotoProdutoCadastrarInputPort inputPort;

    private final FotoProdutoCadastrarMapper mapper;

    @PostMapping(path = {"/{produtoId}/imagem"}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> cadastrarImagem(@PathVariable(name = "produtoId") final Long id,
                                                @Valid FotoProdutoDtoIn dtoIn) {

        Optional.ofNullable(dtoIn)
                .map(mapper::toFotoProdutoBusiness)
                .map(foto -> {
                    inputPort.cadastrarImagem(id, foto);
                    return true;
                })
                .orElseThrow(FotoProdutoCadastrarControllerException::new);

        return ResponseEntity
                .noContent()
                .build();
    }
}

