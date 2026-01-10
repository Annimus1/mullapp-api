package dev.pablo.mullapp.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dev.pablo.mullapp.entities.Artist;
import dev.pablo.mullapp.entities.Genre;

public class ArtistResponseDTO {
    private Long id;
    private String name;
    private String last;
    private String DNI;
    private LocalDate birthday;
    private String avatar;
    private String estate;
    private String municipality;
    private List<String> genres = new ArrayList<>();

    public ArtistResponseDTO(Artist a){
        this.id = a.getId();
        this.name = a.getName();
        this.last = a.getLast();
        this.DNI = a.getDNI();
        this.birthday = a.getBirthday();
        this.avatar = a.getAvatar();
        this.estate = a.getEstate() != null ? a.getEstate().getName() : null;
        this.municipality = a.getMunicipality() != null ? a.getMunicipality().getName() : null;
        Set<Genre> gs = a.getGenres();
        if(gs != null){
            for(Genre g : gs){
                this.genres.add(g.getName());
            }
        }
    }

    public Long getId(){ return id; }
    public String getName(){ return name; }
    public String getLast(){ return last; }
    public String getDNI(){ return DNI; }
    public LocalDate getBirthday(){ return birthday; }
    public String getAvatar(){ return avatar; }
    public String getEstate(){ return estate; }
    public String getMunicipality(){ return municipality; }
    public List<String> getGenres(){ return genres; }
}
