package br.com.desafiogrupoallcross.config.security.dtoin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioLoginDtoIn(

    @NotBlank
    @Email(regexp = "^[a-z0-9_+.]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    String username,

    @NotBlank
    @Size(min = 10, max = 40)
    String password

) { }

