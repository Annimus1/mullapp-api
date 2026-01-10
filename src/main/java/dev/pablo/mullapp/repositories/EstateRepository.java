package dev.pablo.mullapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.pablo.mullapp.entities.Estate;
import dev.pablo.mullapp.entities.EstateNames;

public interface EstateRepository extends JpaRepository<Estate, Long>{
    public Optional<Estate> findByName(EstateNames name);
}
