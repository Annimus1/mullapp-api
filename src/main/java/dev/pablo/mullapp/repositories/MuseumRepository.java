package dev.pablo.mullapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.pablo.mullapp.entities.Museum;

public interface MuseumRepository extends JpaRepository<Museum, Long>{
    public Optional<Museum> findByName(String name);   
}
