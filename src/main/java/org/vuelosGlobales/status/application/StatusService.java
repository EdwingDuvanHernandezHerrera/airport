package org.vuelosGlobales.status.application;

import org.vuelosGlobales.status.domain.Status;
import org.vuelosGlobales.status.infrastructure.StatusRepository;

import java.util.List;
import java.util.Optional;

public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public void createStatus(Status status){
        this.statusRepository.save(status);
    }

    public void updateStatus(Status status){
        this.statusRepository.update(status);
    }

    public Optional<Status> getStatusById(int id){
        return this.statusRepository.findById(id);
    }

    public List<Status> getAllStatuss(){
        return this.statusRepository.findAll();
    }

    public void deleteStatus(int id){
        this.statusRepository.delete(id);
    }
}
