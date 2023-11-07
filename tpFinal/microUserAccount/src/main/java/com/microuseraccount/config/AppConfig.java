package com.microuseraccount.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * AppConfig
 *
 * Esta clase contiene la configuración de la aplicación
 * @author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.microuseraccount.repository")
public class AppConfig {

    /**
     * Bean que permite realizar llamadas a servicios REST
     * @return RestTemplate
     */
    @Bean("RestClient")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Bean que permite la documentación de la API
     * @param description
     * @param version
     * @return OpenAPI
     */
    @Bean("OpenAPI")
    public OpenAPI customOpenAPI(@Value("${application-description}") String description,
                                 @Value("${application-version}") String version) {
        return new OpenAPI()
                .info(new Info().title("UserAccount API")
                        .version(version)
                        .description(description)
                        .license(new License().name("UserAccount API Licence")));
    }
}
