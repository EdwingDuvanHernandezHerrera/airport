package org.vuelosGlobales.city.domain;

public class CityCountryDTO {
    private String id;
    private String nameCity;
    private String nameCountry;

    public CityCountryDTO() {
    }

    public CityCountryDTO(String id, String nameCity, String nameCountry) {
        this.id = id;
        this.nameCity = nameCity;
        this.nameCountry = nameCountry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }
}
