package com.microapigateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

/**
 * RouterValidator
 * Se encarga de verificar si la ruta ingresada requiere de autenticación.
 * @Authors Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 */
@Service
public class RouterValidator {

    public static final List<String> openEndpoints = List.of();

    /**
     * isSecured
     * Valida si la ruta requiere autenticación.
     * @param request
     * @return boolean
     */
    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
