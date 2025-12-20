package dev.pablo.mullapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pablo.mullapp.dto.EstateRequestDTO;
import dev.pablo.mullapp.dto.EstateResponseDTO;
import dev.pablo.mullapp.entities.Estate;
import dev.pablo.mullapp.exceptions.ResourceConflictException;
import dev.pablo.mullapp.services.EstateService;

@RestController
@RequestMapping("/api/estados")
public class EstateController {

    private final EstateService estateService;

    public EstateController(EstateService estateService) {
        this.estateService = estateService;
    }
    
    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllEstates(){
        Map<String,Object> response = new HashMap<>();

        List<EstateResponseDTO> estates = estateService.getAllEstates();
            
        for (EstateResponseDTO e : estates) {
            System.out.println(e.toString());
        }
            
        response.put("estados", estates);        

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Map<String, Object>> setEstates(@RequestBody EstateRequestDTO estateDto){
        Map<String, Object> response = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try{
            Estate estate = estateService.createEstate(estateDto);
            response.put("data",estate.toString());
            response.put("message", "Estado Creado Correctamente");
        }
        catch (ResourceConflictException e){
            httpStatus = HttpStatus.CONFLICT;
            response.put("status", "Error: "+e.getMessage() );
            response.put("status", httpStatus.value());
            response.put("error", "Internal Server Error");
            response.put("message", "El recurso ya existe.");
            response.put("timestamp", java.time.LocalDateTime.now());
        }
        catch (IllegalArgumentException e){
            httpStatus = HttpStatus.BAD_REQUEST;
            response.put("status", "Error: "+e.getMessage() );
            response.put("status", httpStatus.value());
            response.put("error", "Internal Server Error");
            response.put("message", "No es un estado valido.");
            response.put("timestamp", java.time.LocalDateTime.now());
        }
        
        return new ResponseEntity<>(response, httpStatus);
    }
}
