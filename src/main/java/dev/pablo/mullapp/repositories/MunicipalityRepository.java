package dev.pablo.mullapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.pablo.mullapp.entities.Municipality;

public interface MunicipalityRepository extends JpaRepository<Municipality,Long>{
    public Optional<Municipality> findByName(String name);
}
