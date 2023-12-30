package br.com.desafiogrupoallcross.config.security.conversor;

import br.com.desafiogrupoallcross.config.security.dominio.UsuarioBusiness;
import br.com.desafiogrupoallcross.config.security.dtoin.UsuarioCadastrarDtoIn;
import br.com.desafiogrupoallcross.config.security.dtoout.UsuarioCadastrarDtoOut;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioCadastrarMapper {

    UsuarioBusiness toUsuarioBusiness(UsuarioCadastrarDtoIn usuarioCadastrarDtoIn);

    UsuarioCadastrarDtoOut toUsuarioCadastrarDtoOut(UsuarioBusiness usuarioBusiness);
}

