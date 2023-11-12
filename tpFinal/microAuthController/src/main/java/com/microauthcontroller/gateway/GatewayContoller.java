package com.microauthcontroller.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
@ComponentScan(basePackages = {"com.microapigateway"})
public class GatewayContoller {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth-service", r -> r.path("/auth/**")
                .uri("http://localhost:8080"))
            .route("monopatines-service", r -> r.path("/monopatines/**")
                .uri("http://localhost:8002"))
            .route("viajes-service", r -> r.path("/viajes/**")
                .uri("http://localhost:8003"))
            .route("estaciones-service", r -> r.path("/estaciones/**")
                .uri("http://localhost:8001"))
            .route("usuarios-service", r -> r.path("/usuarios/**")
                .uri("http://localhost:8004"))
            .route("cuentas-service", r -> r.path("/cuentas/**")
                .uri("http://localhost:8004"))
            .route("faturas-service", r -> r.path("/administracion/facturacion/**")
                .uri("http://localhost:8005"))
            .route("mantenimiento-service", r -> r.path("/mantenimiento/**")
                .uri("http://localhost:8005"))    
            .build();
    }
}
