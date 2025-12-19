package dev.pablo.mullapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.pablo.mullapp.entities.Estate;

public interface EstateRepository extends JpaRepository<Estate, Long>{
    Optional<Estate> findByName(String name);
}
