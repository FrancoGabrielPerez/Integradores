package com.microapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ApiGatewayConfig
 * Se encarga de enrutar las peticiones a los microservicios correspondientes.
 * @Authors Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Configuration
public class ApiGatewayConfig {
    @Autowired
    AuthenticationFilter filter;

    /**
     * customRouteLocator
     * Enruta las peticiones a los microservicios correspondientes.
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("scooter-service", r -> r.path("/monopatines/**")
                .filters(f -> f.filter(filter))
                .uri("http://localhost:8002"))
            .route("travel-service", r -> r.path("/viajes/**")
                .filters(f -> f.filter(filter))
                .uri("http://localhost:8003"))
            .route("onesestacistaton-service", r -> r.path("/estaciones/**")
                .filters(f -> f.filter(filter))
                .uri("http://localhost:8001"))
            .route("user-service", r -> r.path("/usuarios/**")
                .filters(f -> f.filter(filter))
                .uri("http://localhost:8004"))
            .route("account-service", r -> r.path("/cuentas/**")
                .filters(f -> f.filter(filter))
                .uri("http://localhost:8004"))
            .route("bill-service", r -> r.path("/administracion/facturacion/**")
                .filters(f -> f.filter(filter))
                .uri("http://localhost:8005"))
            .route("administration-service", r -> r.path("/administracion/**")
                .filters(f -> f.filter(filter))
                .uri("http://localhost:8005"))
            .route("maintenance-service", r -> r.path("/mantenimiento/**")
                .filters(f -> f.filter(filter))
                .uri("http://localhost:8005")) 
            .route("authorization-service", r -> r.path("/auth/**")
                .filters(f -> f.filter(filter))
                .uri("http://localhost:8082"))    
            .build();
    }
}
