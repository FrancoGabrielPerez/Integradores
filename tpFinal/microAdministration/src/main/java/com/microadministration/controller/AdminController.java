package com.microadministration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microadministration.dto.FareDTO;
import com.microadministration.dto.StationDTO;
import com.microadministration.dto.NewScooterDTO;
import com.microadministration.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/administracion")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
   
    @Operation(summary = "Da de alta un nuevo monopatin.", description = "Se comunica con el microservicios de monopatines para dar de alta un nuevo monopatin.")
    @PostMapping("monopatines/nuevo")
    public ResponseEntity<?> save(@RequestBody NewScooterDTO scooterDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveNewScooter(scooterDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Da de baja un monopatin.", description = "Se comunica con el microservicios de monopatines para dar de baja un monopatin.")
    @DeleteMapping("monopatines/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteScooter(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Obtiene un informe de lo kilometros recorridos por todos los monopatines", 
                description = "Se comunica con el microservicio de monopatines para obtener un informe de los kilometros recorridos por todos los monopatines.")
    @GetMapping("informes/reporteDeMonopatinesPor/KilometrosRecorridos/sinTiempoDeUso")
    public ResponseEntity<?> getKilometros() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getReportScootersByKms());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Obtiene un informe con los kilometros recorridos y tiempo de uso de cada monopatin.", description = "Se comunica con el microservicio de monopatines para obtener un informe con los kilometros recorridos y tiempo de uso de cada monopatin.")   
    @GetMapping("informes/reporteDeMonopatinesPor/KilometrosRecorridos/conTiempoDeUso")
    public ResponseEntity<?> getKilometrosTiempoUso() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getReportScootersByKmsAndUseTime());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Obtiene un informe de los monopatines que hicieron X cantidad de viajes en determinado año", description = "Se comunica con el microservicio de monopatines para obtener un informe de los monopatines que hicieron X cantidad de viajes en determinado año.")    
    @GetMapping("informes/reporteDeMonopatinesPor/cantidadDeViajes/{travelsQuantity}/enElAnio/{year}")
    public ResponseEntity<?> getReportScootersByTrips(@PathVariable Long travelsQuantity, @PathVariable Integer year) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getScootersWithMoreTravelsInYear(travelsQuantity, year));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Obtiene un informe de los monopatines ordenasdos por tiempo de uso", description = "Se comunica con el microservicio de monopatines para obtener un informe de los monopatines ordenasdos por tiempo de uso.")
    @GetMapping("informes/reporteDeMonopatinesPor/tiempoTotalDeUso")
    public ResponseEntity<?> getReportScootersByUseTime() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getReportScootersByUseTime());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    @Operation(summary = "Agrega una nueva parada.", description = "Se comunica con el microservicios de estaciones para dar de alta una nueva parada.")
    @PostMapping("paradas/nueva")
    public ResponseEntity<?> save(@RequestBody StationDTO stationDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveNewStation(stationDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Elimina una parada", description = "Se comunica con el microservicios de estaciones para eliminar una parada.")
    @DeleteMapping("paradas/eliminar/{id}")
    public ResponseEntity<?> deleteStation(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteStation(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Suspende temporalmente una cuenta.", description = "Se comunica con el microservicios de cuentas para suspender temporalmente una cuenta.")
    @PutMapping("cuentas/suspender/{id}")
    public ResponseEntity<?> suspendAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.suspendAccount(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Activa una cuenta que estaba previamente desactivada.", description = "Se comunica con el microservicios de cuentas para activar una cuenta que estaba previamente desactivada.")
    @PutMapping("cuentas/activar/{id}")
    public ResponseEntity<?> activateAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.activateAccount(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @Operation(summary = "Agrega una nueva tarifa a aplicar desde la fecha dada.", description = "Se comunica con el microservicios de tarifas para agregar una nueva tarifa a aplicar desde la fecha dada.")  
    @PostMapping("tarifas/nueva")
    public ResponseEntity<?> saveNewFare(@RequestBody FareDTO fareDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveNewFare(fareDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
