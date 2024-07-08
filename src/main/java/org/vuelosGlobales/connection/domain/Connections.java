package org.vuelosGlobales.connection.domain;

public class Connections {
    private int id;
    private String connectionNumber;
    private int idTrip;
    private int idPlane;
    private String idAriport;

    public Connections() {
    }

    public Connections(int id, String connectionNumber, int idTrip, int idPlane, String idAriport) {
        this.id = id;
        this.connectionNumber = connectionNumber;
        this.idTrip = idTrip;
        this.idPlane = idPlane;
        this.idAriport = idAriport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConnectionNumber() {
        return connectionNumber;
    }

    public void setConnectionNumber(String connectionNumber) {
        this.connectionNumber = connectionNumber;
    }

    public int getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(int idTrip) {
        this.idTrip = idTrip;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public String getIdAriport() {
        return idAriport;
    }

    public void setIdAriport(String idAriport) {
        this.idAriport = idAriport;
    }
}
