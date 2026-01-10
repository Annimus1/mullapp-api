package dev.pablo.mullapp.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.pablo.mullapp.entities.Artist;
import dev.pablo.mullapp.entities.Estate;
import dev.pablo.mullapp.entities.Municipality;
import dev.pablo.mullapp.repositories.MunicipalityRepository;

@Service
public class MunicipalityService {
    private MunicipalityRepository municipalityRepository;

    public MunicipalityService(MunicipalityRepository municipalityRepository){
        this.municipalityRepository = municipalityRepository;
    }

    public List<Municipality> getAllMunicipalities(){
        return municipalityRepository.findAll();
    }

    public Optional<Municipality> getMunicipality(String name){
        return municipalityRepository.findByName(name);
    }

    public Optional<Municipality> createMunicipalityOptional(String name, Estate estate){

        Optional<Municipality> municipality = getMunicipality(name);
        if(!municipality.isPresent()){
            municipality = Optional.of(municipalityRepository.save(new Municipality(name, estate)));
        }

        return municipality;
    }

    public Municipality createMunicipality(String name, Estate estate){

        Optional<Municipality> municipality = getMunicipality(name);
        if(!municipality.isPresent()){
            municipality = Optional.of(municipalityRepository.save(new Municipality(name, estate)));
        }

        return municipality.get();
    }

    public Municipality update(Municipality municipality, String name, Estate estate, Artist artist){
        Municipality update = municipality;

        if(name != null && name != "" && name != municipality.getName()){
            update.setName(name);
        }
        if(estate != null && estate.getName() != municipality.getEstate().getName()){
            update.setEstate(estate);
        }
        if(artist != null && !municipality.getArtist().contains(artist)){
            Set<Artist> newList = municipality.getArtist();
            newList.add(artist);
            update.setArtist(newList);
        }

        return municipalityRepository.save(update);
    }
}
