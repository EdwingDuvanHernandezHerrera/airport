package org.vuelosGlobales.country.infrastructure;

import org.vuelosGlobales.country.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    void save(Country country);
    void update(Country country);
    Optional<Country> findById(String id);
    List<Country> findAll();
    void delete(String id);

}
