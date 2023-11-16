package com.microadministration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;

import com.microadministration.dto.FareDTO;
import com.microadministration.dto.StationDTO;
import com.microadministration.dto.NewScooterDTO;
import com.microadministration.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

/**
 * AdminController
 * 
 * Clase que contiene los metodos de acceso a la base de datos.
 * @Author Franco Perez, Luciano Melluso, Lautaro Liuzzi, Ruben Marchiori
 * 
 */
@RestController
@RequestMapping("/administracion")
public class AdminController {
    
    @Autowired
    private AdminService adminService;

    @Autowired
	private RestTemplate restTemplate = new RestTemplate();	

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
     * save
     * Crea un nuevo monopatin.
     * @param scooterDTO
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Da de alta un nuevo monopatin.", description = "Se comunica con el microservicios de monopatines para dar de alta un nuevo monopatin.")
    @PostMapping("monopatines/nuevo")
    public ResponseEntity<?> saveScooter(@RequestHeader("Authorization") String token, @RequestBody NewScooterDTO scooterDTO, HttpServletRequest request) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            return restTemplate.exchange("http://localhost:8002/monopatines/alta", HttpMethod.POST, new HttpEntity<>(scooterDTO, headers), String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }


    /**
     * delete
     * Elimina un monoatatin.
     * @param id
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Da de baja un monopatin.", description = "Se comunica con el microservicios de monopatines para dar de baja un monopatin.")
    @DeleteMapping("monopatines/eliminar/{id}")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteScooter(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getKilometros
     * Obtiene un informe de lo kilometros recorridos por todos los monopatines.
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene un informe de lo kilometros recorridos por todos los monopatines", 
                description = "Se comunica con el microservicio de monopatines para obtener un informe de los kilometros recorridos por todos los monopatines.")
    @GetMapping("informes/reporteDeMonopatinesPor/KilometrosRecorridos/sinTiempoDeUso")
    public ResponseEntity<?> getKilometros(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getReportScootersByKms());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getKilometrosTiempoUso
     * Obtiene un informe con los kilometros recorridos y tiempo de uso de cada monopatin.
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene un informe con los kilometros recorridos y tiempo de uso de cada monopatin.", description = "Se comunica con el microservicio de monopatines para obtener un informe con los kilometros recorridos y tiempo de uso de cada monopatin.")   
    @GetMapping("informes/reporteDeMonopatinesPor/KilometrosRecorridos/conTiempoDeUso")
    public ResponseEntity<?> getKilometrosTiempoUso(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getReportScootersByKmsAndUseTime());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getReportScootersByTrips
     * Obtiene un informe de los monopatines que hicieron X cantidad de viajes en determinado año.
     * @param travelsQuantity
     * @param year
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene un informe de los monopatines que hicieron X cantidad de viajes en determinado año", description = "Se comunica con el microservicio de monopatines para obtener un informe de los monopatines que hicieron X cantidad de viajes en determinado año.")    
    @GetMapping("informes/reporteDeMonopatinesPor/cantidadDeViajes/{travelsQuantity}/enElAnio/{year}")
    public ResponseEntity<?> getReportScootersByTrips(@RequestHeader("Authorization") String token, @PathVariable Long travelsQuantity, @PathVariable Integer year) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getScootersWithMoreTravelsInYear(travelsQuantity, year));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * getReportScootersByUseTime
     * Obtiene un reporter de los monopatines ordenasdos por tiempo de uso.
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Obtiene un informe de los monopatines ordenasdos por tiempo de uso", description = "Se comunica con el microservicio de monopatines para obtener un informe de los monopatines ordenasdos por tiempo de uso.")
    @GetMapping("informes/reporteDeMonopatinesPor/tiempoTotalDeUso")
    public ResponseEntity<?> getReportScootersByUseTime(@RequestHeader("Authorization") String token) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN", "MAINTENER"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getReportScootersByUseTime());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    /**
     * save
     * Agrega una nueva parada.
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Agrega una nueva parada.", description = "Se comunica con el microservicios de estaciones para dar de alta una nueva parada.")
    @PostMapping("paradas/nueva")
    public ResponseEntity<?> saveStation(@RequestHeader("Authorization") String token, @RequestBody StationDTO stationDTO) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveNewStation(stationDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * deleteStation
     * Elimina una parada.
     * @param id
     * @return
     */
    @Operation(summary = "Elimina una parada", description = "Se comunica con el microservicios de estaciones para eliminar una parada.")
    @DeleteMapping("paradas/eliminar/{id}")
    public ResponseEntity<?> deleteStation(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteStation(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * suspendAccount
     * Suspende temporalmente una cuenta.
     * @param id
     * @return
     */
    @Operation(summary = "Suspende temporalmente una cuenta.", description = "Se comunica con el microservicios de cuentas para suspender temporalmente una cuenta.")
    @PutMapping("cuentas/suspender/{id}")
    public ResponseEntity<?> suspendAccount(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.suspendAccount(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * activateAccount
     * Activa una cuenta que estaba previamente desactivada.
     * @param id
     * @return
     */
    @Operation(summary = "Activa una cuenta que estaba previamente desactivada.", description = "Se comunica con el microservicios de cuentas para activar una cuenta que estaba previamente desactivada.")
    @PutMapping("cuentas/activar/{id}")
    public ResponseEntity<?> activateAccount(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.activateAccount(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    /**
     * saveNewFare
     * Agrega una nueva tarifa a aplicar desde la fecha dada.
     * @param fareDTO
     * @return ResponseEntity<?>
     */
    @Operation(summary = "Agrega una nueva tarifa a aplicar desde la fecha dada.", description = "Se comunica con el microservicios de tarifas para agregar una nueva tarifa a aplicar desde la fecha dada.")  
    @PostMapping("tarifas/nueva")
    public ResponseEntity<?> saveNewFare(@RequestHeader("Authorization") String token, @RequestBody FareDTO fareDTO) {
        ResponseEntity<String> response = validarToken(token, List.of("ADMIN"));
        if(response.getStatusCode() != HttpStatus.OK){
            return response;
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveNewFare(fareDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
