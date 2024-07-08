package org.vuelosGlobales.trip.domain;

import java.sql.Date;

public class TripAirportDTO {
    private int id;
    private Date tripDate;
    private double priceTrip;
    private String origin;
    private String destination;

    public TripAirportDTO() {
    }

    public TripAirportDTO(int id, Date tripDate, double priceTrip, String origin, String destination) {
        this.id = id;
        this.tripDate = tripDate;
        this.priceTrip = priceTrip;
        this.origin = origin;
        this.destination = destination;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Id: " + id + "\nFecha: " + tripDate + "\nPrecio: " + priceTrip + "\nOrigen: " + origin + "\nDestino: " + destination;
    }
}
