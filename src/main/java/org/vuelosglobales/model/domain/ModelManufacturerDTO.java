package org.vuelosglobales.model.domain;

public class ModelManufacturerDTO extends Model {
    private String nameManufacturer;

    public ModelManufacturerDTO(int id, String name, String nameManufacturer) {
        super(id, name);
        this.nameManufacturer = nameManufacturer;
    }

    public String getNameManufacturer() {
        return nameManufacturer;
    }

    public void setNameManufacturer(String nameManufacturer) {
        this.nameManufacturer = nameManufacturer;
    }

}


