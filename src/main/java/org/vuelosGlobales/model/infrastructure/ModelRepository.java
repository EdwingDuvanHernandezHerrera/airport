package org.vuelosGlobales.model.infrastructure;

import org.vuelosGlobales.model.domain.Model;
import org.vuelosGlobales.model.domain.ModelManufacturerDTO;

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
