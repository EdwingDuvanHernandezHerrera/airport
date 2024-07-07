package org.vuelosGlobales.city.application;

import org.vuelosGlobales.city.domain.CityCountryDTO;
import org.vuelosGlobales.city.infrastructure.CityRepository;
import org.vuelosGlobales.city.domain.City;
import org.vuelosGlobales.country.domain.Country;
import org.vuelosGlobales.country.infrastructure.CountryRepository;

import java.util.List;
import java.util.Optional;

public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public void createCity(City city){
        this.cityRepository.save(city);
    }

    public void updateCity(City city){
        this.cityRepository.update(city);
    }

    public Optional<City> getCityById(String id){
        return this.cityRepository.findById(id);
    }

    public List<City> getAllCities(){
        return this.cityRepository.findAll();
    }

    public void deleteCity(String id){
        this.cityRepository.delete(id);
    }

    public List<CityCountryDTO> getCityWithCountry(){
        return cityRepository.cityWithCountry();
    }

    public Optional<Country> getCountryById(String id){
        return this.countryRepository.findById(id);
    }

    public List<Country> getAllCoutries(){
        return this.countryRepository.findAll();
    }
}
