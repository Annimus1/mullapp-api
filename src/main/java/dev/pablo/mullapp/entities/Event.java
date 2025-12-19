package dev.pablo.mullapp.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "evento")
@Table(name = "evento")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false, length = 50)
    private String name; 

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price; 

    @Column(nullable = false)
    private LocalDate dueDate; 

    @Column(nullable = false)
    private String image; 

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_museo", nullable = false)
    private Museum museum;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_estado", nullable = false)
    private Estate estate;

    @Column(nullable = true, length = 255)
    private String address;

    public Event(){}

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Museum getMuseum() {
        return museum;
    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
    }

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", price=" + price + ", dueDate=" + dueDate + ", image=" + image
                + ", address=" + address + "]";
    }

}
