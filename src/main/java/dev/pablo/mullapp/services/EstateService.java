package dev.pablo.mullapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.pablo.mullapp.dto.EstateRequestDTO;
import dev.pablo.mullapp.dto.EstateResponseDTO;
import dev.pablo.mullapp.entities.Estate;
import dev.pablo.mullapp.entities.EstateNames;
import dev.pablo.mullapp.exceptions.ResourceConflictException;
import dev.pablo.mullapp.repositories.EstateRepository;

@Service
public class EstateService {
    private EstateRepository estateRepository;

    public EstateService(EstateRepository estateRepository) {
        this.estateRepository = estateRepository;
    }

    public Estate createEstate(EstateRequestDTO estateRequestDTO) throws ResourceConflictException{
        Estate estate = new Estate();

        EstateNames enumName = parseToEnum(estateRequestDTO.getName());

        Optional<Estate> existingEstate = estateRepository.findByName(enumName);

        if (existingEstate.isPresent()) {
            throw new ResourceConflictException("El Estado con nombre '" + estateRequestDTO.getName() + "' ya existe.");
        }

        estate.setName(enumName);

        return estateRepository.save(estate);
    }

    public List<EstateResponseDTO> getAllEstates(){
        List<EstateResponseDTO> response = new ArrayList<>();
        List<Estate> estates = estateRepository.findAll();

        for (Estate e : estates) {
            response.add(new EstateResponseDTO(e.getId(), e.getName()));
        }

        return response;
    }
 
    public Optional<Estate> getEstate(String name){
        return estateRepository.findByName(parseToEnum(name));
    }

    private EstateNames parseToEnum(String name) {
        if (name == null) return null;
        String normalized = name.trim().replace(' ', '_').toLowerCase();
        try {
            return EstateNames.valueOf(normalized);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Nombre de estado inválido: " + name, ex);
        }
    }
}
