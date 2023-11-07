package com.microadministration.config;

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
 * Clase que contiene la configuracion de la aplicacion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 *  *
 */
@Configuration
public class AppConfig {

    /**
     * RestTemplate
     * Bean que crea un RestTemplate para realizar peticiones HTTP.
     * @return RestTemplate
     */
    @Bean("RestClient")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * customOpenAPI
     * Bean que crea un OpenAPI para la documentacion de la API.
     * @param description
     * @param version
     * @return OpenAPI
     */
    @Bean("OpenAPI")
    public OpenAPI customOpenAPI(@Value("${application-description}") String description,
                                 @Value("${application-version}") String version) {
        return new OpenAPI()
                .info(new Info().title("Administration API")
                        .version(version)
                        .description(description)
                        .license(new License().name("Administration API Licence")));
    }
}
