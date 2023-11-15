package com.microapigateway.config;

import io.netty.handler.codec.http.HttpMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * AuthenticationFilter
 * Se encarga de filtrar las peticiones que requieren autenticación.
 * @Authors Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    @Autowired
    private RouterValidator validator;

    // URL del servicio de validación de tokens
    private static final String TOKEN_VALIDATION_URL = "http://localhost:8082/auth/validar";
    
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * filter
     * Filtra las peticiones que requieren autenticación.
     * @param exchange
     * @param chain
     * @return Mono<Void>
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
       
        // For testing purposes        
        // System.out.println("Request Path: " + request.getPath());
        // System.out.println("Request: " + request);
        // System.out.println("Request Headers: " + request.getHeaders());
        // System.out.println(validator.isSecured.test(request));
        // End testing purposes

        if (validator.isSecured.test(request)) {
            if (authMissing(request)) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            final String tokenRequest = request.getHeaders().getOrEmpty("Authorization").get(0);
            // System.out.println("Token: " + tokenRequest);
            String token = null;
            if (tokenRequest != null && tokenRequest.startsWith("Bearer ")) {
                token = tokenRequest.substring(7);
            }  
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(token, headers); 
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(TOKEN_VALIDATION_URL, entity, String.class);        
           
            //System.out.println("Token: " + token);
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            exchange = responseEntity.getHeaders().get("Authorization") != null ? 
                                    exchange.mutate().request(request.mutate().header("Authorization", 
                                    responseEntity.getHeaders().get("Authorization").get(0)).build()).build() : exchange;
        }
        return chain.filter(exchange);
    }

    /**
     * onError
     * Devuelve un error en caso de que la petición no sea válida.
     * @param exchange
     * @param httpStatus
     * @return Mono<Void>
     */
    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    /**
     * authMissing
     * Devuelve true si la petición no contiene el header Authorization.
     * @param request
     * @return
     */
    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
}
