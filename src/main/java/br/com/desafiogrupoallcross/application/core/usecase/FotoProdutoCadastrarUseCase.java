package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoCadastrarInputPort;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoSalvarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.FotoProdutoCadastrarUseCaseException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class FotoProdutoCadastrarUseCase implements FotoProdutoCadastrarInputPort {

    private final FotoProdutoSalvarOutputPort salvarOutputPort;

    public FotoProdutoCadastrarUseCase(FotoProdutoSalvarOutputPort salvarOutputPort) {
        this.salvarOutputPort = salvarOutputPort;
    }

    @Override
    public void cadastrarImagem(Long id, FotoProdutoBusiness fotoProdutoBusiness) {

        Optional.ofNullable(fotoProdutoBusiness)
            .ifPresentOrElse(foto -> {
                this.extrairMetadadosDaFoto(foto);
                salvarOutputPort.cadastrarImagem(id, foto);
                },
                () -> {throw new FotoProdutoCadastrarUseCaseException();}
            );
    }

    private void extrairMetadadosDaFoto(FotoProdutoBusiness fotoBusiness) {
        MultipartFile foto = fotoBusiness.getFoto();

        fotoBusiness.setNome(foto.getOriginalFilename());
        fotoBusiness.setTipo(foto.getContentType());
        fotoBusiness.setTamanho(foto.getSize());
    }
}

