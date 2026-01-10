package dev.pablo.mullapp.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.pablo.mullapp.dto.ArtistRequestDTO;
import dev.pablo.mullapp.dto.ArtistResponseDTO;
import dev.pablo.mullapp.dto.EstateRequestDTO;
import dev.pablo.mullapp.entities.Artist;
import dev.pablo.mullapp.entities.Estate;
import dev.pablo.mullapp.entities.Genre;
import dev.pablo.mullapp.entities.Municipality;
import dev.pablo.mullapp.exceptions.MissingArgumentsException;
import dev.pablo.mullapp.exceptions.ResourceConflictException;
import dev.pablo.mullapp.services.ArtistService;
import dev.pablo.mullapp.services.EstateService;
import dev.pablo.mullapp.services.GenreService;
import dev.pablo.mullapp.services.MunicipalityService;

@RestController
@RequestMapping("api/artistas")
public class ArtistController {
    
    private ArtistService artistService;
    private EstateService estateService;
    private MunicipalityService municipalityService;
    private GenreService genreService;

    public ArtistController(ArtistService artistService, 
                            EstateService estateService,
                            MunicipalityService municipalityService,
                            GenreService genreService){
        this.artistService = artistService;
        this.estateService = estateService;
        this.municipalityService = municipalityService;
        this.genreService = genreService;
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllArtists(){
        HttpStatus httpStatus = HttpStatus.OK;
        Map<String, Object> response = new HashMap<>();
        try{            
            List<ArtistResponseDTO> artists = artistService.getAllArtists();
            
            response.put("data", artists);
        }
        catch (Exception e ){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.put("status", "error");
            response.put("status", e.getMessage());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getArtist(@PathVariable String id){
        HttpStatus httpStatus = HttpStatus.OK;
        Map<String, Object> response = new HashMap<>();
        try{
            Long lid = Long.parseLong(id);
            Optional<ArtistResponseDTO> opt = artistService.getArtistById(lid);
            if(!opt.isPresent()){
                httpStatus = HttpStatus.NOT_FOUND;
                response.put("status", "error");
                response.put("message", "Artist not found");
            } else {
                response.put("data", opt.get());
            }
        }
        catch (NumberFormatException e ){
            httpStatus = HttpStatus.BAD_REQUEST;
            response.put("status", "error");
            response.put("message", "Invalid id");
        }
        catch (Exception e ){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.put("status", "error");
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> CreateArtist(@RequestBody ArtistRequestDTO request){
        HttpStatus httpStatus = HttpStatus.CREATED;
        Map<String, Object> response = new HashMap<>();
        try{
            // verify all params are present
            ArtistRequestDTO.evalArtist(request);

            // get estate
            Optional<Estate> estate = estateService.getEstate(request.getEstate());
            if(!estate.isPresent()){
                estate = Optional.of(estateService.createEstate(new EstateRequestDTO(request.getEstate())));
            }
            
            // get Municiality
            Optional<Municipality> municipality = municipalityService.getMunicipality(request.getMunicipality());
            if(!municipality.isPresent()){
                municipality = municipalityService.createMunicipalityOptional(request.getMunicipality(), estate.get());
            }

            // create artist
            Artist artistAux = new Artist();
            artistAux.setName(request.getName());
            artistAux.setLast(request.getLast());
            artistAux.setDNI(request.getDNI());
            artistAux.setBirthday(request.getBirthday());
            artistAux.setEstate(estate.get());
            artistAux.setMunicipality(municipality.get());

            if(request.getAvatar() != null){
                artistAux.setAvatar(request.getAvatar());
            }

            // Set genres before saving the artist
            Set<Genre> genres = new HashSet<>();
            if(request.getGenres() != null){
                for(String s : request.getGenres()){
                    Genre g = genreService.getOrCreateByName(s);
                    genres.add(g);
                }
            }
            artistAux.setGenres(genres);

            Artist artist = artistService.createArtist(artistAux);

            ArtistResponseDTO artistResponseDTO = new ArtistResponseDTO(artist);

            // send response
            response.put("data", artistResponseDTO);
        }
        catch (MissingArgumentsException e){
            httpStatus = HttpStatus.BAD_REQUEST;
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        catch (ResourceConflictException e){
            httpStatus = HttpStatus.CONFLICT;
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        catch (Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.put("status", "error");
            System.out.println("error: "+ "Un error inesperado ha ocurrido.");
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PatchMapping("/{id}")
    //TODO
    public ResponseEntity<Map<String, Object>> UpdateArtist(@PathVariable String id, @RequestBody Map<String, Object> body){
        HttpStatus httpStatus = HttpStatus.OK;
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("id", id);
            response.put("body", body);
        }
        catch (MissingArgumentsException e){
            httpStatus = HttpStatus.BAD_REQUEST;
            response.put("status", "error");
            response.put("message", e.getMessage());
        }
        catch (Exception e){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.put("status", "error");
            System.out.println("error: "+ "Un error inesperado ha ocurrido.");
        }

        return new ResponseEntity<>(response, httpStatus);
    }

}
