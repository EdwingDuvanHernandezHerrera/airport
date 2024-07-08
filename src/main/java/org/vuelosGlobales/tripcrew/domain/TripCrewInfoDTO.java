package org.vuelosGlobales.tripcrew.domain;

public class TripCrewInfoDTO {
    private String nameEmployee;
    private String rolEmployee;
    private String connectionNumber;
    private String airport;

    public TripCrewInfoDTO() {
    }

    public TripCrewInfoDTO(String nameEmployee, String rolEmployee, String connectionNumber, String airport) {
        this.nameEmployee = nameEmployee;
        this.rolEmployee = rolEmployee;
        this.connectionNumber = connectionNumber;
        this.airport = airport;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getRolEmployee() {
        return rolEmployee;
    }

    public void setRolEmployee(String rolEmployee) {
        this.rolEmployee = rolEmployee;
    }

    public String getConnectionNumber() {
        return connectionNumber;
    }

    public void setConnectionNumber(String connectionNumber) {
        this.connectionNumber = connectionNumber;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }
}
