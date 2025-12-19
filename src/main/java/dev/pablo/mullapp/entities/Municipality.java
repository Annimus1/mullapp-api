package dev.pablo.mullapp.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "municipio")
@Table(name = "municipio")
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name; // 50 characters max

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_estado", nullable = false)
    private Estate estate; 

    @OneToMany(mappedBy = "municipality", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Artist> artist;

    public Municipality(){}

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

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public Set<Artist> getArtist() {
        return artist;
    }

    public void setArtist(Set<Artist> artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Municipality [id=" + id + ", name=" + name + ", estate=" + estate.getName() + "]";
    }
    
}
