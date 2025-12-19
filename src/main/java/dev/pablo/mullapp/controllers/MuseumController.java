package dev.pablo.mullapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pablo.mullapp.dto.EstateRequestDTO;
import dev.pablo.mullapp.dto.MuseumRequestDTO;
import dev.pablo.mullapp.dto.MuseumResponseDTO;
import dev.pablo.mullapp.entities.Estate;
import dev.pablo.mullapp.exceptions.ResourceNotFoundException;
import dev.pablo.mullapp.services.EstateService;
import dev.pablo.mullapp.services.MuseumService;

@RestController
@RequestMapping("api/museos")
public class MuseumController {

    private MuseumService museumService;
    private EstateService estateService;

    public MuseumController(MuseumService museumService, EstateService estateService){
        this.museumService = museumService;
        this.estateService = estateService;
    }

    @GetMapping("")
    public ResponseEntity<Map<String,Object>> getAllMuseums(){
        Map<String, Object> response = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try{

            List<MuseumResponseDTO> museums = museumService.getMuseums();


            response.put("Museos",museums);
        }
        catch(Exception e){
            httpStatus = HttpStatus.NOT_FOUND;
            response.put("status", "Error: "+e.getMessage() );
            response.put("status", httpStatus.value());
            response.put("error", "Internal Server Error");
            response.put("message", "Ha ocurrido un error inesperado.");
            response.put("timestamp", java.time.LocalDateTime.now());
        }


        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getMuseum(@PathVariable String id){
        Map<String, Object> response = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{
            Long LongId = Long.parseLong(id);
            MuseumResponseDTO museum = museumService.findById(LongId);
            response.put("data", museum);
        }
        catch (NumberFormatException e){
            httpStatus = HttpStatus.BAD_REQUEST;
            response.put("status", "error");
            response.put("status", httpStatus.value());
            response.put("message", "El id debe ser un numero.");
            response.put("timestamp", java.time.LocalDateTime.now());
        }
        catch (ResourceNotFoundException e){
            httpStatus = HttpStatus.NOT_FOUND;
            response.put("status", "error");
            response.put("status", httpStatus.value());
            response.put("message", "Recurso no encontrado.");
            response.put("timestamp", java.time.LocalDateTime.now());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("")
    public ResponseEntity<Map<String,Object>> createMuseum(@RequestBody MuseumRequestDTO museumRequestDTO){
        Map<String, Object> response = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.CREATED;
        Estate estate = null;
        MuseumResponseDTO museum = null;

        try{
            Optional<Estate> isestate = estateService.getEstate(museumRequestDTO.getEstate());

            if(isestate.isPresent()){
                estate = isestate.get();
            }

            // if estate doesn't exist we create a new one.
            if(isestate.isEmpty()){
                EstateRequestDTO estateRequestDTO = new EstateRequestDTO();
                estateRequestDTO.setName(museumRequestDTO.getEstate());

                estate = estateService.createEstate(estateRequestDTO);
            }

            museum = museumService.creatMuseum(museumRequestDTO.getName(), estate);
            response.put("data",museum);
        }
        catch(Exception e){
            httpStatus = HttpStatus.CONFLICT;
            response.put("status", "Error: "+e.getMessage() );
            response.put("status", httpStatus.value());
            response.put("error", "Internal Server Error");
            response.put("message", "El Recurso ya existe.");
            response.put("timestamp", java.time.LocalDateTime.now());
        }


        return new ResponseEntity<>(response, httpStatus);
    }
    
}
