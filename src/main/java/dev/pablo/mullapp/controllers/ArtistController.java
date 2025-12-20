package dev.pablo.mullapp.controllers;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pablo.mullapp.services.ArtistService;
import dev.pablo.mullapp.services.EstateService;
import dev.pablo.mullapp.services.MuseumService;

@RestController
@RequestMapping("api/artistas")
public class ArtistController {
    
    private ArtistService artistService;
    private EstateService estateService;
    private MuseumService museumService;

    public ArtistController(ArtistService artistService, 
                            EstateService estateService, 
                            MuseumService museumService){
        this.artistService = artistService;
        this.estateService = estateService;
        this.museumService = museumService;
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllArtists(){
        HttpStatus httpStatus = HttpStatus.OK;
        Map<String, Object> response = new HashMap<>();
        
        response.put("data", "ok");

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getArtist(@PathVariable String id){
        HttpStatus httpStatus = HttpStatus.OK;
        Map<String, Object> response = new HashMap<>();
        
        response.put("data", id);

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> CreateArtist(){
        HttpStatus httpStatus = HttpStatus.CREATED;
        Map<String, Object> response = new HashMap<>();
        
        response.put("data", "ok");

        return new ResponseEntity<>(response, httpStatus);
    }
}
