package com.microscooter.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
@Configuration
public class AppConfig {

    @Bean("RestClient")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

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