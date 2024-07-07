package org.vuelosGlobales.airport.domain;

public class AirportCityDTO {
    private String id;
    private String nameAirport;
    private String nameCity;

    public AirportCityDTO() {
    }

    public AirportCityDTO(String  id, String nameAirport, String nameCity) {
        this.id = id;
        this.nameAirport = nameAirport;
        this.nameCity = nameCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameAirport() {
        return nameAirport;
    }

    public void setNameAirport(String nameAirport) {
        this.nameAirport = nameAirport;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Aeropuerto: "+ nameAirport + ", Ciudad: " + nameCity;
    }
}
