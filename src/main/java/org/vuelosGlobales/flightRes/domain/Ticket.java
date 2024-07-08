package org.vuelosGlobales.flightRes.domain;

import com.mysql.cj.exceptions.StreamingNotifiable;

public class Ticket {
    private int idReserva;
    private String name;
    private String lastName;
    private int nroId;
    private int seat;
    private String descriptionFare;
    private Double value;

    public Ticket() {
    }

    public Ticket(int idReserva, String name, String lastName, int nroId, int seat, String descriptionFare, Double value) {
        this.idReserva = idReserva;
        this.name = name;
        this.lastName = lastName;
        this.nroId = nroId;
        this.seat = seat;
        this.descriptionFare = descriptionFare;
        this.value = value;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNroId() {
        return nroId;
    }

    public void setNroId(int nroId) {
        this.nroId = nroId;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getDescriptionFare() {
        return descriptionFare;
    }

    public void setDescriptionFare(String descriptionFare) {
        this.descriptionFare = descriptionFare;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
