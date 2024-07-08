package org.vuelosGlobales.flightRes.domain;

public class FlightRes {
    private int id;
    private String date;
    private int idTrip;

    public FlightRes() {
    }

    public FlightRes(int id, String date, int idTrip) {
        this.id = id;
        this.date = date;
        this.idTrip = idTrip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }
}
