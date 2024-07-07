package org.vuelosglobales.model.application;

import java.util.List;
import java.util.Optional;

import org.vuelosglobales.manufacturer.domain.Manufacturer;
import org.vuelosglobales.manufacturer.infrastructure.ManufacturerRepository;
import org.vuelosglobales.model.domain.Model;
import org.vuelosglobales.model.domain.ModelManufacturerDTO;
import org.vuelosglobales.model.infrastructure.ModelRepository;

public class ModelService {

    private final ModelRepository modelRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ModelService(ModelRepository modelRepository, ManufacturerRepository manufacturerRepository) {
        this.modelRepository = modelRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public void saveModel(Model model){
        modelRepository.save(model);
    }

    public void updateModel(Model model){
        modelRepository.update(model);
    }

    public void deleteModel(int id){
        modelRepository.delete(id);
    }

    public List<Model> findAllModel(){
        return  modelRepository.findAll();
    }

    public Optional<Model> findByIdModel(int id){
        return modelRepository.findById(id);
    }

    public List<Manufacturer> findAllManufacturer(){
        return manufacturerRepository.findAll();
    }

    public Optional<Manufacturer> findManufacturerById(int id){
        return manufacturerRepository.findById(id);
    }

    public List<ModelManufacturerDTO> findAllModelManufacturerDTO(){
        return modelRepository.findAllDTO();
    }
}
