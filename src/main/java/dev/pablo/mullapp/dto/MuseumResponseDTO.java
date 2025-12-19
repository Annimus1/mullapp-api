package dev.pablo.mullapp.dto;

public class MuseumResponseDTO {
    private String name;
    private String estate;
    private Long id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEstate() {
        return estate;
    }
    public void setEstate(String estate) {
        this.estate = estate;
    }
    public Long getId() {
        return id;
    }
    public void setName(Long id) {
        this.id = id;
    }
    public MuseumResponseDTO(Long id, String name, String estate) {
        this.name = name;
        this.estate = estate;
        this.id = id;
    }

}
