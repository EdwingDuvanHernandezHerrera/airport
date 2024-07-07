package org.vuelosGlobales.airline.application;

import org.vuelosGlobales.airline.domain.Airline;
import org.vuelosGlobales.airline.infrastructure.AirlineRepository;

import java.util.List;
import java.util.Optional;

public class AirlineService {
    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }
    public void createAirline(Airline airline){
        this.airlineRepository.save(airline);
    }

    public void updateAirline(Airline airline){
        this.airlineRepository.update(airline);
    }

    public Optional<Airline> getAirlineById(int id){
        return this.airlineRepository.findById(id);
    }

    public List<Airline> getAllAirlines(){
        return this.airlineRepository.findAll();
    }

    public void deleteAirline(int id){
        this.airlineRepository.delete(id);
    }
}
