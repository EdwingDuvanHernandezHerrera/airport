package org.vuelosGlobales.manufacturer.application;

import java.util.List;
import java.util.Optional;

import org.vuelosGlobales.manufacturer.domain.Manufacturer;
import org.vuelosGlobales.manufacturer.infrastructure.ManufacturerRepository;

public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public void saveManufacturer(Manufacturer manufacturer){
        manufacturerRepository.save(manufacturer);
    }

    public void updateManufacturer(Manufacturer manufacturer){
        manufacturerRepository.update(manufacturer);
    }

    public void deleteManufacturer(int id){
        manufacturerRepository.delete(id);
    }

    public List<Manufacturer> findAllManufacturer(){
        return  manufacturerRepository.findAll();
    }

    public Optional<Manufacturer> findByIdManufacturer(int id){
        return manufacturerRepository.findById(id);
    }
}
