package dev.pablo.mullapp.dto;

public class MuseumRequestDTO {
    private String name;
    private String estate;

    public MuseumRequestDTO (){}

    public MuseumRequestDTO(String name, String estate){
        this.name = name;
        this.estate = estate;
    }

    public String getName(){ return name;}

    public String getEstate(){ return estate;}

    public void setName(String name){ this.name = name;}

    public void setEstate(String estate){ this.estate = estate;}
}
