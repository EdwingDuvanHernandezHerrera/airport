package org.vuelosGlobales.airline.domain;

public class Airline {
    private int id;
    private String name;

    public Airline() {
    }

    public Airline(int id, String nombre) {
        this.id = id;
        this.name = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name;
    }
}
