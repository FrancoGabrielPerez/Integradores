package com.microtravel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * AppConfig
 * 
 * Configuracion de la aplicacion.
 * @Author Luciano Melluso, Franco Perez, Lautaro Liuzzi, Ruben Marchiori
 */
@Configuration
public class AppConfig {

    /**
     * RestTemplate
     * Bean para realizar peticiones HTTP.
     * @return
     */
    @Bean("RestClient")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * customOpenAPI
     * Bean para la documentacion de la API.
     * @param description
     * @param version
     */
    @Bean("OpenAPI")
    public OpenAPI customOpenAPI(@Value("${application-description}") String description,
                                 @Value("${application-version}") String version) {
        return new OpenAPI()
                .info(new Info().title("Travel API")
                        .version(version)
                        .description(description)
                        .license(new License().name("Travel API Licence")));
    }
}
