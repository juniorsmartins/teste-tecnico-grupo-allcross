package br.com.desafiogrupoallcross.adapter.in.controller;

import br.com.desafiogrupoallcross.adapter.in.dto.filtro.ProdutoDtoFiltro;
import br.com.desafiogrupoallcross.adapter.in.dto.request.ProdutoCadastrarDtoIn;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoCadastrarDtoOut;
import br.com.desafiogrupoallcross.adapter.in.dto.response.ProdutoPesquisarDtoOut;
import br.com.desafiogrupoallcross.adapter.in.mapper.ProdutoMapper;
import br.com.desafiogrupoallcross.adapter.in.mapper.ProdutoFiltroMapper;
import br.com.desafiogrupoallcross.application.port.in.ProdutoCadastrarInputPort;
import br.com.desafiogrupoallcross.application.port.in.ProdutoPesquisarInputPort;
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
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoCadastrarInputPort inputPort;

    private final ProdutoPesquisarInputPort pesquisarInputPort;

    private final ProdutoMapper mapper;

    private final ProdutoFiltroMapper filtroMapper;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<ProdutoCadastrarDtoOut> cadastrar(@RequestBody @Valid ProdutoCadastrarDtoIn dtoIn) {

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
    public ResponseEntity<Page<ProdutoPesquisarDtoOut>> pesquisar(final ProdutoDtoFiltro produtoDtoFiltro,
        @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10) final Pageable paginacao) {

        var paginaDtoOut = Optional.ofNullable(produtoDtoFiltro)
                .map(filtro -> {
                    System.out.println("\n\n 1 -> " + filtro + "\n");
                    return filtro;
                })
                .map(filtroMapper::toProdutoFiltro)
                .map(filtro -> {
                    System.out.println("\n\n 2 -> " + filtro + "\n");
                    return filtro;
                })
                .map(filtro -> this.pesquisarInputPort.pesquisar(filtro, paginacao))
                .map(filtro -> {
                    System.out.println("\n\n 3 -> " + filtro + "\n");
                    return filtro;
                })
                .map(pagina -> pagina.map(this.mapper::toProdutoPesquisarDtoOut))
                .map(filtro -> {
                    System.out.println("\n\n 4 -> " + filtro + "\n");
                    return filtro;
                })
                .orElseThrow();

        return ResponseEntity
                .ok()
                .body(paginaDtoOut);
    }
}

