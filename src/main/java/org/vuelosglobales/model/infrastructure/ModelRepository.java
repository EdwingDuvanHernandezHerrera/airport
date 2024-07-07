package org.vuelosglobales.model.infrastructure;

import org.vuelosglobales.model.domain.Model;
import org.vuelosglobales.model.domain.ModelManufacturerDTO;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {

    void save(Model model);
    void update(Model model);
    void delete(int id);
    List<Model> findAll();
    Optional<Model> findById(int id);
    List<ModelManufacturerDTO> findAllDTO();

}
