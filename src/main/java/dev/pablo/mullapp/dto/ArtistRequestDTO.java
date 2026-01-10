package dev.pablo.mullapp.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dev.pablo.mullapp.entities.Work;
import dev.pablo.mullapp.exceptions.MissingArgumentsException;

public class ArtistRequestDTO {
    private Long id;
    private String name;
    private String last;
    private String DNI;
    private LocalDate birthday;
    private String avatar;
    private Set<Work> works;
    private String estate;
    private String municipality;
    private Set<String> genres;

    public ArtistRequestDTO(){}

    public ArtistRequestDTO(String name, String last, String DNI, String birthday, String estate, String municipality){
        this.name = name;
        this.last = last;
        this.DNI = DNI;
        this.birthday = parseDate(birthday);
        this.estate = estate;
        this.municipality = municipality;
    }
    
    public ArtistRequestDTO(String name, String last, String DNI, String birthday, String estate, String municipality, String avatar, Set<String> genres ){
        this.name = name;
        this.last = last;
        this.DNI = DNI;
        this.birthday = parseDate(birthday);
        this.estate = estate;
        this.municipality = municipality;
        this.avatar = avatar;
        this.genres = genres;
    }

    /**
     * Validate required fields of an ArtistRequestDTO.
     *
     * This method verifies that the following fields are present (not null and not empty):
     * name, last, birthday, estate, municipality and museum. If one or more of these fields
     * are missing the method throws a MissingArgumentsException that contains a human-readable
     * message listing the missing parameter names (message text follows the project's Spanish
     * phrasing).
     *
     * @param artistDTO the ArtistRequestDTO to validate; must be non-null
     * @throws MissingArgumentsException when one or more required fields are absent or empty
     * @throws NullPointerException if artistDTO is null (caller responsibility)
     */
    public static void evalArtist(ArtistRequestDTO artistDTO) throws MissingArgumentsException{

        List<String> missing = new ArrayList<>();

        if(artistDTO.name == null || artistDTO.name == ""){
            missing.add("name");
        }

        if(artistDTO.last == null || artistDTO.last == ""){
            missing.add("last");
        }

        if(artistDTO.birthday == null){
            missing.add("birthday");
        }

        if(artistDTO.estate == null || artistDTO.estate == ""){
            missing.add("estate");
        }

        if(artistDTO.municipality == null || artistDTO.municipality == ""){
            missing.add("municipality");
        }

        if(artistDTO.DNI == null || artistDTO.DNI == ""){
            missing.add("DNI");
        }

        if(missing.size()>0){
            String text = missing.size() == 1 ? "Falta el parametro" : "Faltan los parametros";

            for (String s : missing) {
                text = text + " "+ "'"+s+"'";
            }

            text = text + ".";

            throw new MissingArgumentsException(text);
        }
        System.out.println("Has all artist arguments.");
        System.out.println(artistDTO.toString());
    }

    /**
     * Conver a String into LocalDate type.
     * @param date String in format YYYY/MM/DD or YYYY-MM-DD.
     * @return Date in format LocalDate.
     * @throws IllegalArgumentException when date format is incorrect.
     */
    public LocalDate parseDate(String date){
        String[] parts = new String[3];
        if(date.contains("-")){
            parts = date.split("-");
        }
        if(date.contains("/")){
            parts = date.split("/");
        }

        if (parts.length == 0) {
            throw new IllegalArgumentException("Fecha en formato Incorrecto");
        }

        return LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
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

    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) {
        DNI = dNI;
    }  
      
}
