package dev.pablo.mullapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.pablo.mullapp.entities.Artist;
import dev.pablo.mullapp.entities.Genre;
import dev.pablo.mullapp.repositories.GenresRepository;

@Service
public class GenreService {
    private GenresRepository genresRepository;

    public GenreService(GenresRepository genresRepository){
        this.genresRepository = genresRepository;
    }

    public Genre create(String name, String description){
        Genre genre = new Genre();

        genre.setName(name.toLowerCase());
        genre.setDescription(description.toLowerCase());

        return genre;
    }

    public Genre create(String name){
        Genre genre = new Genre();

        genre.setName(name.toLowerCase());

        return genre;
    }

    public List<Genre> getAll(){
        return genresRepository.findAll();
    }

    public Genre getOrCreateByName(String name){
        if(name == null) throw new IllegalArgumentException("name null");
        String n = name.toLowerCase();
        Optional<Genre> opt = genresRepository.findByName(n);
        if(opt.isPresent()) return opt.get();
        Genre g = new Genre();
        g.setName(n);
        return genresRepository.save(g);
    }

    public List<Genre> getAll(Artist artist){
        List<Genre> genres = new ArrayList<>();
        
        return genres;
    } 
}
