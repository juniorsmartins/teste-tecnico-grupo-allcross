package br.com.desafiogrupoallcross.utilitarios;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockMultipartFile;

@Configuration
public class JacksonConfig {

    @Bean
    public SimpleModule mockMultipartFileModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(MockMultipartFile.class, new MockMultipartFileSerializer());
        return module;
    }
}

