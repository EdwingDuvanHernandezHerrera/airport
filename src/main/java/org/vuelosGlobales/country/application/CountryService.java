package org.vuelosGlobales.country.application;

import org.vuelosGlobales.country.domain.Country;
import org.vuelosGlobales.country.infrastructure.CountryRepository;

import java.util.List;
import java.util.Optional;

public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void createCountry(Country country){
        this.countryRepository.save(country);
    }

    public void updateCountry(Country country){
        this.countryRepository.update(country);
    }

    public Optional<Country> getCountryById(String id){
        return this.countryRepository.findById(id);
    }

    public List<Country> getAllCoutries(){
        return this.countryRepository.findAll();
    }

    public void deleteCountry(String id){
        this.countryRepository.delete(id);
    }
}
