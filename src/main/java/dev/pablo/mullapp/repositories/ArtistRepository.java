package dev.pablo.mullapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.pablo.mullapp.entities.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long>{
    
}