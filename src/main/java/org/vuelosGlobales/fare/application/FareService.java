package org.vuelosGlobales.fare.application;

import org.vuelosGlobales.fare.domain.Fare;
import org.vuelosGlobales.fare.infrastructure.FareRepository;

import java.util.List;
import java.util.Optional;

public class FareService {
    private final FareRepository fareRepository;

    public FareService(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }

    public void createFare(Fare fare){
        this.fareRepository.save(fare);
    }

    public void updateFare(Fare fare){
        this.fareRepository.update(fare);
    }

    public Optional<Fare> getFareById(int id){
        return this.fareRepository.findById(id);
    }

    public List<Fare> getAllFares(){
        return this.fareRepository.findAll();
    }

    public void deleteFare(int id){
        this.fareRepository.delete(id);
    }
}
