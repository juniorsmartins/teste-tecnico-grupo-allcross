package br.com.desafiogrupoallcross.application.core.usecase;

import br.com.desafiogrupoallcross.application.core.domain.FotoProdutoBusiness;
import br.com.desafiogrupoallcross.application.port.in.FotoProdutoCadastrarInputPort;
import br.com.desafiogrupoallcross.application.port.out.FotoProdutoSalvarOutputPort;
import br.com.desafiogrupoallcross.config.exception.http_400.FotoProdutoCadastrarUseCaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class FotoProdutoCadastrarUseCase implements FotoProdutoCadastrarInputPort {

    private static final Logger log = LoggerFactory.getLogger(FotoProdutoCadastrarUseCase.class);

    private final FotoProdutoSalvarOutputPort outputPort;

    public FotoProdutoCadastrarUseCase(FotoProdutoSalvarOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Override
    public void cadastrarImagem(Long id, FotoProdutoBusiness fotoProdutoBusiness) {

        log.info("Iniciado serviço para cadastrar imagem de Produto com Id: {}.", id);

        Optional.ofNullable(fotoProdutoBusiness)
            .ifPresentOrElse(foto -> {
                this.extrairMetadadosDaFoto(foto);
                outputPort.salvar(id, foto);
                },
                () -> {throw new FotoProdutoCadastrarUseCaseException();}
            );

        log.info("Finalizado serviço para cadastrar imagem de Produto com Id: {}.", id);
    }

    private void extrairMetadadosDaFoto(FotoProdutoBusiness fotoBusiness) {
        MultipartFile foto = fotoBusiness.getFoto();

        fotoBusiness.setNome(foto.getOriginalFilename());
        fotoBusiness.setTipo(foto.getContentType());
        fotoBusiness.setTamanho(foto.getSize());
    }
}

