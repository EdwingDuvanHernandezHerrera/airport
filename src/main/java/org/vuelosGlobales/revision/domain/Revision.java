package org.vuelosGlobales.revision.domain;


import java.sql.Date;

public class Revision {
    private int id;
    private Date revisionDate;
    private int idPlane;
    private String description;

    public Revision() {
    }

    public Revision(int id, Date revisionDate, int idPlane, String description) {
        this.id = id;
        this.revisionDate = revisionDate;
        this.idPlane = idPlane;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
