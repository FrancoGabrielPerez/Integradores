package com.microscooter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.microscooter.dto.ScooterDTO;
import com.microscooter.dto.StationDTO;
import com.microscooter.service.ScooterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * ScooterController
 * 
 * Clase que contiene los metodos de acceso a la base de datos de Monopatines.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@RestController
@RequestMapping("/monopatines")
public class ScooterController {
    
    @Autowired
    private ScooterService scooterService;

    // URL del servicio de validación de tokens
    private static final String TOKEN_VALIDATION_URL = "http://localhost:8081/auth/validar";

    /**
     * validarToken
     * Valida el token antes de realizar cualquier operación.
     * @param token
     */
    private boolean validarToken(String token) {
        // Realizar una llamada al servicio de validación de tokens
        // System.out.println("Validando token: " + token);
        ResponseEntity<String> response = new RestTemplate().postForEntity(TOKEN_VALIDATION_URL, token, String.class);
        // System.out.println("Respuesta: " + response);
        return response.getStatusCode() == HttpStatus.OK;
    }

    /**
     * getAll
     * Obtiene todos los monopatines.
     * @return List<ScooterDTO>
     */
    @Operation(summary = "Obtiene todas los monopatines.", description = "Obtiene todos los monopatines")
    @SecurityRequirement(name = "Authorization")
    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestHeader("Authorization") String token){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * save
     * Agrega un monopatin. 
     * @param entity
     * @return ScooterDTO
     */
    @Operation(summary = "Agrega un monopatin.", description = "Agrega un monopatin")
    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String token, @RequestBody ScooterDTO entity){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{            
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    /**
     * getById
     * Obtiene un monopatin por su id.
     * @param scooterId
     * @return ScooterDTO
     */
    @Operation(summary = "Obtiene un monopatin por su id.", description = "Obtiene un monopatin por su scooterId")
    @GetMapping("/{scooterId}")
    public ResponseEntity<?> getById(@RequestHeader("Authorization") String token,@PathVariable long scooterId) {
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findById(scooterId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * delete
     * Elimina un monopatin por su id.
     * @param scooterId
     * @return
     */
    @Operation(summary = "Eliminia un monopatin por su id.", description = "Elimina una monopatin por su scooterId")
    @DeleteMapping("/eliminar/{scooterId}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token,@PathVariable long scooterId){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{
            scooterService.delete(scooterId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente el monopatin con scooterId: " + scooterId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el monopatin, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * update
     * Actualiza los datos de un monopatin por su id.
     * @param scooterId
     * @param entity
     * @return
     */
    @Operation(summary = "Actualiza los datos de un monopatin por su id.", description = "Actualiza un monopatin por su scooterId")
    @PutMapping("/actualizar/{scooterId}")
    public ResponseEntity<?> update(@RequestHeader("Authorization") String token,@PathVariable long scooterId, @RequestBody ScooterDTO entity){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }
        try{
            scooterService.update(scooterId,entity);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizaron correctamente los datos del monopatin con scooterId: " + entity.getScooterId());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudieron actualizar los datos de la estacion, revise los campos e intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * scooterEnEstacion
     * Verifica si un monopatin esta en una parada.
     * @param scooterId
     * @return
     */
    @Operation(summary = "Verifica si un monopatin esta en una parada.", description = "Verifica si un monopatin esta en una parada.")
    @GetMapping("/estacion/{scooterId}")
    public ResponseEntity<?> scooterEnEstacion(@RequestHeader("Authorization") String token,@PathVariable long scooterId){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }

        try{
            StationDTO estacion = scooterService.scooterEnEstacion(scooterId);
            if (estacion != null) {
                return ResponseEntity.status(HttpStatus.OK).body("El monopatin se encuentra en la estacion ubicada en : " + estacion.getLatitud() + ", " + estacion.getLongitud());
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body("El monopatin no se encuentra en ninguna estacion.");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getReporteByKilometros
     * Obtiene un reporte de monopatines ordenados por kilometros.
     * @return
     */
    @Operation(summary = "Obtengo un reporte de monopatines ordenados por kilometros", description = "Obtengo un reporte de monopatines ordenados por kilometros")
    @GetMapping("/reporte/kilometros/sinTiempoDeUso")
    public ResponseEntity<?> getReporteByKilometros(@RequestHeader("Authorization") String token){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findByKilometros());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getReporteByKilometrosConTiempoUso
     * Obtiene un reporte de monopatines por kilometros y tiempo de uso.
     * @return
     */
    @Operation(summary = "Obtiene un reporte de monopatines por kilometros y tiempo de uso.", description = "Obtiene un reporte de monopatines por kilometros y tiempo de uso")
    @GetMapping("/reporte/kilometros/conTiempoDeUso")
    public ResponseEntity<?> getReporteByKilometrosConTiempoUso(@RequestHeader("Authorization") String token){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findByKilometrosConTiempoUso());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getReporteByTiempoUso
     * Obtiene un reporte de monopatines ordenados por tiempo de uso.
     * @return
     */
    @Operation(summary = "Obtengo un reporte de monopatines ordenados por tiempo de uso", description = "Obtengo un reporte de monopatines ordenados por tiempo de uso")
    @GetMapping("/reporte/tiempoUso")
    public ResponseEntity<?> getReporteByTiempoUso(@RequestHeader("Authorization") String token){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findByTiempoUso());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }   

    /**
     * getReporteByTiempoTotal
     * Obtiene un reporte de monopatines ordenados por tiempo total (En uso + En pausa).
     * @return
     */
    @Operation(summary = "Obtengo un reporte de monopatines ordenados por tiempo total (En uso + En pausa)", description = "Obtengo un reporte de monopatines ordenados por tiempo total (En uso + En pausa)")
    @GetMapping("/reporte/tiempoTotal")
    public ResponseEntity<?> getReporteByTiempoTotal(@RequestHeader("Authorization") String token){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findByTiempoTotal());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }  

    /**
     * getReporteOperativosMantenimiento
     * Obtiene un reporte de cantidad de monopatines operativos vs en mantenimiento.
     * @return
     */
    @Operation(summary = "Obtengo un reporte de cantidad de monopatines operativos vs en mantenimiento", description = "Obtengo un reporte de cantidad de monopatines operativos vs en mantenimiento")
    @GetMapping("/reporte/cantidadOperativosMantenimiento")
    public ResponseEntity<?> getReporteOperativosMantenimiento(@RequestHeader("Authorization") String token){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.findOperativosMantenimiento());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }  

    /**
     * getAllCercanos
     * Obtiene todos los monopatines cerca de una coordenada.
     * @param latitud
     * @param longitud
     * @return
     */
    @Operation(summary = "Obtiene todos los monopatines.", 
                description = "Obtiene todos los monopatines cerca de una coordenada (ejemplo que funciona latitud -37.327754, longitud -59.138998)")
    @GetMapping("/{latitud}/{longitud}")
    public ResponseEntity<?> getAllCercanos(@RequestHeader("Authorization") String token,@PathVariable Double latitud, @PathVariable Double longitud){
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no válido");
        }

        try{
            return ResponseEntity.status(HttpStatus.OK).body(scooterService.getScootersCercanos(latitud,longitud));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}