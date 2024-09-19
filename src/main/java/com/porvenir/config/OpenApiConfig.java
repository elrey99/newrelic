package com.porvenir.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .components(new Components())
            .info(new Info()
            .version("1.0.0")
            .title("Servicio ahl-ms-agrupacion-empledaor-java")
            .description("Descripcion del servicio")
            .contact(new Contact().name("Team Tech Comunidad Digital Comercial").url("teamtechCDC@porvenir.com.co")));
    }

}
