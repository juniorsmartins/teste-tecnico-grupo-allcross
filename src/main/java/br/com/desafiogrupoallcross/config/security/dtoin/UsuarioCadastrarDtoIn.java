package br.com.desafiogrupoallcross.config.security.dtoin;

import br.com.desafiogrupoallcross.config.security.papeis.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioCadastrarDtoIn(

    @NotBlank
    @Email(regexp = "^[a-z0-9_+.]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    String username,

    @NotBlank
    @Size(min = 10, max = 40)
    String password,

    @NotNull
    RoleEnum role

) { }

