package dev.pablo.mullapp.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "artista")
@Table(name = "artista")
public class Artist{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;
    
    @Column(nullable = true, length = 20)
    private String last;
    
    @Column(nullable = false)
    private LocalDate birthday;
    
    @Column(nullable = true)
    private String avatar;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    private Set<Work> works;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_estado", nullable = false)
    private Estate estate;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipality municipality;

    @ManyToMany
    @JoinTable(
        name = "artista_genero", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "id_artista"),
        inverseJoinColumns = @JoinColumn(name = "id_genero") 
    )
    private Set<Genre> genres;

    public Artist(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Work> getWorks() {
        return works;
    }

    public void setWorks(Set<Work> works) {
        this.works = works;
    }

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Artist [id=" + id + ", name=" + name + ", last=" + last + ", birthday=" + birthday + ", avatar="
                + avatar  + ", estate=" + estate.getName() + ", municipality=" + municipality.getName() + "]";
    }

    
}
