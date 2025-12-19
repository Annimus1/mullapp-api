package dev.pablo.mullapp.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "estado")
@Table(name = "estado")
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 12)
    private String name; // 12 characters max

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Municipality> municipalities;

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Museum> museum;

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Artist> artist;

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Event> event;

    public Estate(){
    }

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

    public Set<Municipality> getMunicipalities() {
        return municipalities;
    }

    public void setMunicipalities(Set<Municipality> municipalities) {
        this.municipalities = municipalities;
    }

    public Set<Museum> getMuseum() {
        return museum;
    }

    public void setMuseum(Set<Museum> museum) {
        this.museum = museum;
    }

    public Set<Artist> getArtist() {
        return artist;
    }

    public void setArtist(Set<Artist> artist) {
        this.artist = artist;
    }

    public Set<Event> getEvent() {
        return event;
    }

    public void setEvent(Set<Event> event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Estate [id=" + id + ", name=" + name + "]";
    }
    
}
