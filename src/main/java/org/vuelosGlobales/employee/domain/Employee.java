package org.vuelosGlobales.employee.domain;

import com.mysql.cj.util.StringInspector;

public class Employee {
    private String id;
    private String nombre;
    private String ingressDate;
    private int idRol;
    private int idAirline;
    private String idAirport;

    public Employee() {
    }

    public Employee(String id, String nombre, String ingressDate, int idRol, int idAirline, String idAirport) {
        this.id = id;
        this.nombre = nombre;
        this.ingressDate = ingressDate;
        this.idRol = idRol;
        this.idAirline = idAirline;
        this.idAirport = idAirport;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getIngressDate() {
        return ingressDate;
    }

    public void setIngressDate(String ingressDate) {
        this.ingressDate = ingressDate;
    }

    public int getIdAirline() {
        return idAirline;
    }

    public void setIdAirline(int idAirline) {
        this.idAirline = idAirline;
    }

    public String getIdAirport() {
        return idAirport;
    }

    public void setIdAirport(String idAirport) {
        this.idAirport = idAirport;
    }
}
