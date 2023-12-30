package br.com.desafiogrupoallcross.config.security.servico;

import br.com.desafiogrupoallcross.config.security.dominio.UsuarioBusiness;
import br.com.desafiogrupoallcross.config.security.portas.in.UsuarioCadastrarInputPort;
import br.com.desafiogrupoallcross.config.security.portas.out.UsuarioSalvarOutputPort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UsuarioCadastrarUseCase implements UsuarioCadastrarInputPort {

    private final UsuarioSalvarOutputPort cadastrarOutputPort;

    private final PasswordEncoder passwordEncoder;

    public UsuarioCadastrarUseCase(UsuarioSalvarOutputPort cadastrarOutputPort, PasswordEncoder passwordEncoder) {
        this.cadastrarOutputPort = cadastrarOutputPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioBusiness cadastrar(UsuarioBusiness usuarioBusiness) {

        return Optional.ofNullable(usuarioBusiness)
                .map(this::encriptografarPassword)
                .map(this.cadastrarOutputPort::salvar)
                .orElseThrow();
    }

    private UsuarioBusiness encriptografarPassword(UsuarioBusiness usuarioBusiness) {
        var senhaParaEncriptar = usuarioBusiness.getPassword();
        var senhaEncriptografada = this.passwordEncoder.encode(senhaParaEncriptar);
        usuarioBusiness.setPassword(senhaEncriptografada);
        return usuarioBusiness;
    }
}

