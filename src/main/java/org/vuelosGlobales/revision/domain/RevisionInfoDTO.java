package org.vuelosGlobales.revision.domain;

public class RevisionInfoDTO {
    private int id;
    private String revisionDate;
    private String nameEmployee;
    private String platePlane;
    private String modelPlane;
    private String description;

    public RevisionInfoDTO() {
    }

    public RevisionInfoDTO(int id, String revisionDate, String nameEmployee, String platePlane, String modelPlane, String description) {
        this.id = id;
        this.revisionDate = revisionDate;
        this.nameEmployee = nameEmployee;
        this.platePlane = platePlane;
        this.modelPlane = modelPlane;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getPlatePlane() {
        return platePlane;
    }

    public void setPlatePlane(String platePlane) {
        this.platePlane = platePlane;
    }

    public String getModelPlane() {
        return modelPlane;
    }

    public void setModelPlane(String modelPlane) {
        this.modelPlane = modelPlane;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
