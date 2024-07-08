package org.vuelosGlobales.connection.domain;

public class ConnInfoDTO {
    private int idTrip;
    private int idConn;
    private String ConnNumber;
    private String plates;
    private String nameCityAirport;

    public ConnInfoDTO() {
    }

    public ConnInfoDTO(int idTrip, int idConn, String connNumber, String plates, String nameCityAirport) {
        this.idTrip = idTrip;
        this.idConn = idConn;
        ConnNumber = connNumber;
        this.plates = plates;
        this.nameCityAirport = nameCityAirport;
    }

    public int getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }

    public int getIdConn() {
        return idConn;
    }

    public void setIdConn(int idConn) {
        this.idConn = idConn;
    }

    public String getConnNumber() {
        return ConnNumber;
    }

    public void setConnNumber(String connNumber) {
        ConnNumber = connNumber;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String getNameCityAirport() {
        return nameCityAirport;
    }

    public void setNameCityAirport(String nameCityAirport) {
        this.nameCityAirport = nameCityAirport;
    }
}