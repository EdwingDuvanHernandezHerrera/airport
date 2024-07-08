package org.vuelosGlobales.customer.domain;

public class CustomerDocuDTO {
    private int id;
    private String nameCustomer;
    private String lastName;
    private int nroId;
    private int age;
    private String nameDocument;

    public CustomerDocuDTO() {
    }

    public CustomerDocuDTO(int id, String nameCustomer, String lastName, int nroId, int age, String nameDocument) {
        this.id = id;
        this.nameCustomer = nameCustomer;
        this.lastName = lastName;
        this.nroId = nroId;
        this.age = age;
        this.nameDocument = nameDocument;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public int getNroId() {
        return nroId;
    }

    public void setNroId(int nroId) {
        this.nroId = nroId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNameDocument() {
        return nameDocument;
    }

    public void setNameDocument(String nameDocument) {
        this.nameDocument = nameDocument;
    }
}
