package dev.pablo.mullapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.pablo.mullapp.entities.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long>{
    public List<Artist> findByName(String Name); 
}