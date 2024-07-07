package org.vuelosGlobales.status.infrastructure;

import org.vuelosGlobales.status.domain.Status;

import java.util.List;
import java.util.Optional;

public interface StatusRepository {
    void save(Status status);
    void update(Status status);
    Optional<Status> findById(int id);
    List<Status> findAll();
    void delete(int id);
}
