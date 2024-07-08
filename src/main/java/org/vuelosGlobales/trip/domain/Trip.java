package org.vuelosGlobales.trip.domain;

import java.sql.Date;

public class Trip {
    private int id;
    private Date tripDate;
    private double priceTrip;
    private String idOrigin;
    private String idDestination;

    public Trip() {
    }

    public Trip(int id, Date tripDate, double priceTrip, String idOrigin, String idDestination) {
        this.id = id;
        this.tripDate = tripDate;
        this.priceTrip = priceTrip;
        this.idOrigin = idOrigin;
        this.idDestination = idDestination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public double getPriceTrip() {
        return priceTrip;
    }

    public void setPriceTrip(double priceTrip) {
        this.priceTrip = priceTrip;
    }

    public String getIdOrigin() {
        return idOrigin;
    }

    public void setIdOrigin(String idOrigin) {
        this.idOrigin = idOrigin;
    }

    public String getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(String idDestination) {
        this.idDestination = idDestination;
    }
}
