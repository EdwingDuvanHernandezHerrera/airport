package org.vuelosGlobales.passenger.domain;

import org.vuelosGlobales.customer.domain.Customer;

public class Passenger extends Customer {
    private int seat;
    private int idTripBookingDetails;
    public Passenger() {
    }

    public Passenger(int id, String name, String lastName, int nroId, int age, int idDocument, int seat, int idTripBookingDetails) {
        super(id, name, lastName, nroId, age, idDocument);
        this.seat = seat;
        this.idTripBookingDetails = idTripBookingDetails;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getIdTripBookingDetails() {
        return idTripBookingDetails;
    }

    public void setIdTripBookingDetails(int idTripBookingDetails) {
        this.idTripBookingDetails = idTripBookingDetails;
    }
}
