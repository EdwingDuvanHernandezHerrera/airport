package org.vuelosGlobales.airport.application;

import org.vuelosGlobales.city.domain.City;
import org.vuelosGlobales.city.infrastructure.CityRepository;
import org.vuelosGlobales.airport.domain.Airport;
import org.vuelosGlobales.airport.domain.AirportCityDTO;
import org.vuelosGlobales.airport.infrastructure.AirportRepository;

import java.util.List;
import java.util.Optional;

public class AirportService {
    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    public AirportService(AirportRepository airportRepository, CityRepository cityRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }

    public void createAirport(Airport airport){
        this.airportRepository.save(airport);
    }

    public void updateAirport(Airport airport){
        this.airportRepository.update(airport);
    }

    public Optional<Airport> getAirportById(String id){
        return this.airportRepository.findById(id);
    }

    public List<Airport> getAllAirports(){
        return this.airportRepository.findAll();
    }

    public void deleteAirport(String id){
        this.airportRepository.delete(id);
    }
    public Optional<City> getCityById(String id){
        return this.cityRepository.findById(id);
    }

    public List<City> getAllCities(){
        return this.cityRepository.findAll();
    }

    public List<AirportCityDTO> getAllAirportCity(){
        return this.airportRepository.findAllAirportCity();
    }

    public Optional<AirportCityDTO> getAirportCityById(String  id){
        return this.airportRepository.findAirportCityById(id);
    }

}
