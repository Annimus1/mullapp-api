package dev.pablo.mullapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.pablo.mullapp.entities.Genre;

public interface GenresRepository extends JpaRepository<Genre, Long>{
    public Optional<Genre> findByName(String Name);
}
