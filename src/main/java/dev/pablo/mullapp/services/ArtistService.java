package dev.pablo.mullapp.services;

import org.springframework.stereotype.Service;

import dev.pablo.mullapp.repositories.ArtistRepository;

@Service
public class ArtistService {
    private ArtistRepository artistsRepository;

    public ArtistService(ArtistRepository artistsRepository){
        this.artistsRepository = artistsRepository;
    }

    
}
