package org.vuelosGlobales.plane.domain;

import java.sql.Date;

public class PlaneStMdDTO {
    private int id;
    private String plates;
    private int capacity;
    private Date fabricationDate;
    private String nameAirline;
    private String nameStatus;
    private String nameModel;

    public PlaneStMdDTO() {
    }

    public PlaneStMdDTO(int id, String plates, int capacity, Date fabricationDate, String nameAirline, String nameStatus, String nameModel) {
        this.id = id;
        this.plates = plates;
        this.capacity = capacity;
        this.fabricationDate = fabricationDate;
        this.nameAirline = nameAirline;
        this.nameStatus = nameStatus;
        this.nameModel = nameModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getFabricationDate() {
        return fabricationDate;
    }

    public void setFabricationDate(Date fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public String getNameAirline() {
        return nameAirline;
    }

    public void setNameAirline(String nameAirline) {
        this.nameAirline = nameAirline;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    @Override
    public String toString() {
        return "PlaneStMdDTO{" +
                "id=" + id +
                ", plates='" + plates + '\'' +
                ", capacity=" + capacity +
                ", fabricationDate='" + fabricationDate + '\'' +
                ", nameStatus='" + nameStatus + '\'' +
                ", nameModel='" + nameModel + '\'' +
                '}';
    }
}
