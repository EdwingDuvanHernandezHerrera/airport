package org.vuelosGlobales.city.infrastructure;

import org.vuelosGlobales.city.domain.City;
import org.vuelosGlobales.city.domain.CityCountryDTO;

import java.util.List;
import java.util.Optional;

public interface CityRepository {
    void save(City city);
    void update(City city);
    Optional<City> findById(String id);
    List<City> findAll();
    void delete(String id);
    List<CityCountryDTO> cityWithCountry();
}
