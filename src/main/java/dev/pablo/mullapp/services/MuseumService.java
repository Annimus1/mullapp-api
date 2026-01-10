package dev.pablo.mullapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.pablo.mullapp.dto.MuseumResponseDTO;
import dev.pablo.mullapp.entities.Estate;
import dev.pablo.mullapp.entities.Museum;
import dev.pablo.mullapp.exceptions.ResourceConflictException;
import dev.pablo.mullapp.exceptions.ResourceNotFoundException;
import dev.pablo.mullapp.repositories.MuseumRepository;

@Service
public class MuseumService {
    
    private MuseumRepository museumRepository;


    public MuseumService(MuseumRepository museumRepository){
        this.museumRepository = museumRepository;
    }

    public List<MuseumResponseDTO> getMuseums(){
        List<Museum> museums = museumRepository.findAll();
        List<MuseumResponseDTO> museumsresponse = new ArrayList<>();

        for (Museum m : museums) {
            museumsresponse.add(new MuseumResponseDTO(m.getId(), m.getName(), m.getEstate().getName()));
        }

        return museumsresponse;
    }

    public MuseumResponseDTO creatMuseum(String name, Estate estate) throws ResourceConflictException{
        
        Museum museum = new Museum();
        museum.setName(name);
        museum.setEstate(estate);

        Optional<Museum> existingMuseum = museumRepository.findByName(name);

        if(existingMuseum.isPresent()){
            throw new ResourceConflictException("Resource already exists.");
        }

        Museum response = museumRepository.save(museum);
        return new MuseumResponseDTO(response.getId(), response.getName(), response.getEstate().getName());
        
    }

        public Museum creatMuseumOptional(String name, Estate estate) throws ResourceConflictException{
        
        Museum museum = new Museum();
        museum.setName(name);
        museum.setEstate(estate);

        Optional<Museum> existingMuseum = museumRepository.findByName(name);

        if(existingMuseum.isPresent()){
            throw new ResourceConflictException("Resource already exists.");
        }

        Museum response = museumRepository.save(museum);
        return response;
        
    }

    public MuseumResponseDTO findById(Long id) throws ResourceNotFoundException{
        MuseumResponseDTO museum = null;
        Optional<Museum> existingMuseum = museumRepository.findById(id);

        if (existingMuseum.isPresent()) {
            museum = new MuseumResponseDTO(existingMuseum.get().getId(), existingMuseum.get().getName(), existingMuseum.get().getEstate().getName());
        }

        if(museum == null){
            throw new ResourceNotFoundException("Resource not found.");
        }
        return museum;
    }

    public Optional<Museum> getMuseum(String name){
        return museumRepository.findByName(name);
    }
}
