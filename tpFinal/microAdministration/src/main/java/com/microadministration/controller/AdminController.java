package com.microadministration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microadministration.dto.BillDTO;
import com.microadministration.dto.ScooterDTO;
import com.microadministration.service.BillService;
import com.microadministration.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/administracion")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
   
    @Operation(summary = "Da de alta un nuevo monopatin.", description = "Da de alta un nuevo monopatin.")
    @PostMapping("administracion/nuevoMonopatin")
    public ResponseEntity<?> save(@RequestBody ScooterDTO scooterDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveNewScooter(scooterDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

}
