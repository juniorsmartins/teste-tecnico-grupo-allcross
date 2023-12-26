package br.com.desafiogrupoallcross.adapter.in.dto.filtro;

import br.com.desafiogrupoallcross.adapter.out.entity.enuns.TipoCategoriaEnum;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public final class CategoriaFiltro {

    private String id;

    private String nome;

    private Boolean ativo;

    private TipoCategoriaEnum tipo;
}

