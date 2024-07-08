package org.vuelosGlobales.customer.domain;

public class Customer {
    private int id;
    private String name;
    private String lastName;
    private int nroId;
    private int age;
    private int idDocument;

    public Customer() {
    }

    public Customer(int id, String name, String lastName, int nroId, int age, int idDocument) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.nroId = nroId;
        this.age = age;
        this.idDocument = idDocument;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }
}
