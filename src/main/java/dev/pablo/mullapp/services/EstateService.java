package dev.pablo.mullapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.pablo.mullapp.dto.EstateRequestDTO;
import dev.pablo.mullapp.dto.EstateResponseDTO;
import dev.pablo.mullapp.entities.Estate;
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

        Optional<Estate> existingEstate = estateRepository.findByName(estateRequestDTO.getName().toLowerCase());

        if (existingEstate.isPresent()) {
            throw new ResourceConflictException("El Estado con nombre '" + estateRequestDTO.getName() + "' ya existe.");
        }

        estate.setName(estateRequestDTO.getName().toLowerCase());

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
        Optional<Estate> estate = estateRepository.findByName(name.toLowerCase());
        return estate;
    }
}
