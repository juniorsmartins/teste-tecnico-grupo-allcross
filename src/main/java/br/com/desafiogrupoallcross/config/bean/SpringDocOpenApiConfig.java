package br.com.desafiogrupoallcross.config.bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
            .info(new Info()
                .title("Teste Técnico Grupo AllCross - API Rest")
                .description("API para gestão de Produtos. Com Java 21 LTS e Spring Boot 3.0.12")
                .version("v1")
                .termsOfService("http://teste.com.br/terms-of-service") // URL fictícia
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0"))
                .contact(new Contact()
                    .name("Junior Martins")
                    .email("teste@email.com"))
            );
    }
}

