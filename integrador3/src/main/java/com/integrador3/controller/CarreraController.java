package com.integrador3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.integrador3.dto.CarreraDTO;
import com.integrador3.service.CarreraService;

@RestController
@RequestMapping("/carreras")
public class CarreraController {
    
    @Autowired
    private CarreraService carreraService;

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PostMapping("/alta")
    public ResponseEntity<?> save(@RequestBody CarreraDTO entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestBody Long id){
        try{
            carreraService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino correctamente la carrera con id: " + id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar la carrera, revise los campos e intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }

    @PostMapping("/matricular")
    public ResponseEntity<?> matricular(@RequestParam Integer libreta, @RequestParam String carrera){ 
        try{
            carreraService.matricular(libreta, carrera);
            return ResponseEntity.status(HttpStatus.OK).body("Se matriculo correctamente el estudiante con LU: " + libreta + " en la carrera: " + carrera);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo matricular el estudiante, revise los campos e intente nuevamente.\"\n\"error\":\""+ex.getMessage()+"\"}");
        }
    }

    @PostMapping("/desmatricular")
    public ResponseEntity<?> desmatricular(@RequestParam Integer libreta, @RequestParam String carrera){
        try{
            carreraService.desmatricular(libreta, carrera);
            return ResponseEntity.status(HttpStatus.OK).body("Se desmatriculo correctamente el estudiante: con LU: " + libreta + " en la carrera: " + carrera);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo desmatricular el estudiante, revise los campos e intente nuevamente.\"\n\"error\":\""+ex.getMessage()+"\"}");
        }
    }

    @GetMapping("/carrerasPorCantEstudiantes")
    public ResponseEntity<?> carrerasOrdenadas() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.carrerasOrdenadas());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    
    @GetMapping("/informeCarreras")
    public ResponseEntity<?> carrerasPorInscriptos() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.informeCarreras());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
}
