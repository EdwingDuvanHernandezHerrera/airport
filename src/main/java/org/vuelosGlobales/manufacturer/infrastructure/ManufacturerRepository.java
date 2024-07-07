package org.vuelosGlobales.manufacturer.infrastructure;

import org.vuelosGlobales.manufacturer.domain.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerRepository {

    void save(Manufacturer manufacturer);
    void update(Manufacturer manufacturer);
    void delete(int id);
    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(int id);

}
