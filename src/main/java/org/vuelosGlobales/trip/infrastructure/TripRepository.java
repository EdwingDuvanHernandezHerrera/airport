package org.vuelosGlobales.trip.infrastructure;

import org.vuelosGlobales.trip.domain.Trip;
import org.vuelosGlobales.trip.domain.TripAirportDTO;

import java.util.List;
import java.util.Optional;

public interface TripRepository {

    int save(Trip trip);
    void update(Trip trip);
    Optional<Trip> findById(int id);
    List<Trip> findAll();
    void delete(int id);
    List<TripAirportDTO> findAllTripAirport();
    Optional<TripAirportDTO> findTripAirportById(int id);
}
