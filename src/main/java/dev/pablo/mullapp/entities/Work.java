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
import jakarta.persistence.Table;

@Entity(name = "obras")
@Table(name = "obras")
public class Work{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nombre; // 60 characters max
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genero", nullable = true) // id_genero era nulo en tu SQL, lo mantenemos así.
    private Genre genre;

    @Column(nullable = false)
    private LocalDate date; 

    @Column(nullable = false, length = 20)
    private String dimensiones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formato", nullable = true) // id_formato era nulo en tu SQL, lo mantenemos así.
    private Format format;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artista", nullable = false)
    private Artist artist; 

    @ManyToMany
    @JoinTable(
        name = "obra_museo",
        joinColumns = @JoinColumn(name="id_obra"),
        inverseJoinColumns = @JoinColumn(name="id_museo")
    )
    private Set<Museum> museums;

    public Work(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Set<Museum> getMuseums() {
        return museums;
    }

    public void setMuseums(Set<Museum> museums) {
        this.museums = museums;
    }

    @Override
    public String toString() {
        return "Work [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", genre=" + genre.getName() + ", date="
                + date + ", dimensiones=" + dimensiones + ", format=" + format.getName() + ", imagen=" + imagen + ", artist="
                + artist.getName() + "]";
    }

}
