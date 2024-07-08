package org.vuelosGlobales.flightRes.domain;

public class ReservationByCustomer {
    private int idReservacion;
    private String date;
    private Double price;
    private String origin;
    private String destination;

    public ReservationByCustomer() {
    }

    public ReservationByCustomer(int idReservacion, String date, Double price, String origin, String destination) {
        this.idReservacion = idReservacion;
        this.date = date;
        this.price = price;
        this.origin = origin;
        this.destination = destination;
    }

    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
}
