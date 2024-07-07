package org.vuelosglobales.manufacturer.domain;


public class Manufacturer {
    private  int id;
    private String name;

    public Manufacturer() {
    }

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String separator = String.format("|%s|%s|", "-".repeat(12), "-".repeat(22));
        String record = String.format("| %-10d | %-20s |", id, name);

        return  separator + "\n" + record;
    }
    
}

