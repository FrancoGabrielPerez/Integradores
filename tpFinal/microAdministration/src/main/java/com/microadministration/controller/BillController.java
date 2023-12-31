package com.microadministration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.microadministration.dto.NewBillDTO;
import com.microadministration.service.BillService;
import io.swagger.v3.oas.annotations.Operation;

/**
 * BillController
 * 
 * Clase que contiene los metodos de acceso a la base de datos de Facturacion.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@RestController
@RequestMapping("administracion/facturacion")
public class BillController {
    
    @Autowired
    private BillService billService;

    // URL del servicio de validación de tokens
    private static final String TOKEN_VALIDATION_URL = "http://localhost:8082/auth/validar";

    /**
     * validarToken
     * Valida el token antes de realizar cualquier operación.
     * @param token
     * @param roles Lista de roles validos para el endpoint
     * @return ResponseEntity<String>
     */
    private ResponseEntity<String> validarToken(String token, List<String> roles) {
        ResponseEntity<String> response = new RestTemplate().postForEntity(TOKEN_VALIDATION_URL, token, String.class);
        if (response.getStatusCode() != HttpStatus.OK){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        if(!(roles.contains(response.getBody()))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tipo de usuario no valido");
        }
        return response;
    }
   
    /**
     * getAll
     * Obtiene todas las facturas.
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene todas las facturas.", description = "Obtiene todas las facturas.")
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(billService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getOne
     * Obtiene una factura por id.
     * @param id
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene una factura por id.", description = "Obtiene una factura por id.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@RequestHeader("Authorization") String token, @PathVariable Long id){
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(billService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * save
     * @param entity
     * @return ResponseEntity<?>
     */ 
    @Operation(summary = "Carga una nueva factura.", description = "Carga una nueva factura.")
    @PostMapping("/nueva")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @RequestBody NewBillDTO NewBillDTO) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(billService.save(NewBillDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getFacturacion
     * Obtiene la facturacion en un rango de fechas.
     * @param id
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtener facturacion en un rango de fechas.", description = "Obtener facturacion en un rango de fechas. Formato de fecha: yyyy-mm-dd")
    @GetMapping("/fechaDesde/{fechaDesde}/fechaHasta/{fechaHasta}")   
    public ResponseEntity<?> getFacturacion(@RequestHeader("Authorization") String token, @PathVariable String fechaDesde, @PathVariable String fechaHasta) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(billService.getFacturacion(fechaDesde, fechaHasta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
