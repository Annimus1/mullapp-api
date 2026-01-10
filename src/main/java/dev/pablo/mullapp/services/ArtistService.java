package dev.pablo.mullapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.pablo.mullapp.dto.ArtistResponseDTO;
import dev.pablo.mullapp.entities.Artist;
import dev.pablo.mullapp.exceptions.ResourceConflictException;
import dev.pablo.mullapp.repositories.ArtistRepository;

@Service
public class ArtistService {
    private ArtistRepository artistsRepository;

    public ArtistService(ArtistRepository artistsRepository) {
        this.artistsRepository = artistsRepository;
    }

    public List<ArtistResponseDTO> getAllArtists() {
        List<Artist> artists = artistsRepository.findAll();
        List<ArtistResponseDTO> response = new ArrayList<>();

        for (Artist a : artists) {
            response.add(new ArtistResponseDTO(a));
        }

        return response;
    }

    public Artist createArtist(Artist artist) throws ResourceConflictException {
        List<Artist> artists = artistsRepository.findByName(artist.getName());
        Boolean error = false;

        if (artists.size() > 0) {
            for (Artist a : artists) {
                if(artist.getDNI().equals(a.getDNI())){
                    error = true;
                }
            }
        }

        if (error) {
            throw new ResourceConflictException("Recurso ya creado: [ Nombre="+artist.getName() 
            +" Apellido=" + artist.getLast() + " DNI=" + artist.getDNI() +" ]" );
        }
        return artistsRepository.save(artist);
    }

    public Optional<ArtistResponseDTO> getArtistById(Long id){
        Optional<Artist> opt = artistsRepository.findById(id);
        if(opt.isPresent()){
            return Optional.of(new ArtistResponseDTO(opt.get()));
        }
        return Optional.empty();
    }
}
