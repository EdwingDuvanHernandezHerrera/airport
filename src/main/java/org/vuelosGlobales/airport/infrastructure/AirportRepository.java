package org.vuelosGlobales.airport.infrastructure;

import org.vuelosGlobales.airport.domain.Airport;
import org.vuelosGlobales.airport.domain.AirportCityDTO;

import java.util.List;
import java.util.Optional;

public interface AirportRepository {
    void save(Airport airport);
    void update(Airport airport);
    Optional<Airport> findById(String id);
    List<Airport> findAll();
    void delete(String id);
    List<AirportCityDTO> findAllAirportCity();
    Optional<AirportCityDTO> findAirportCityById(String id);
}
