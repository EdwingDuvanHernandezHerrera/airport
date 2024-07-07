package org.vuelosGlobales.employee.domain;

public class EmployeeRelationshipDTO {
    private String id;
    private String name;
    private String ingressDate;
    private String rolName;
    private String airlineName;
    private String airportName;

    public EmployeeRelationshipDTO() {
    }

    public EmployeeRelationshipDTO(String id, String name, String ingressDate, String rolName, String airlineName, String airportName) {
        this.id = id;
        this.name = name;
        this.ingressDate = ingressDate;
        this.rolName = rolName;
        this.airlineName = airlineName;
        this.airportName = airportName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngressDate() {
        return ingressDate;
    }

    public void setIngressDate(String ingressDate) {
        this.ingressDate = ingressDate;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
}
