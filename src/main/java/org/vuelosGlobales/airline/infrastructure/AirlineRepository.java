package org.vuelosGlobales.airline.infrastructure;

import org.vuelosGlobales.airline.domain.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository {
    void save(Airline airline);
    void update(Airline airline);
    Optional<Airline> findById(int id);
    List<Airline> findAll();
    void delete(int id);
}
