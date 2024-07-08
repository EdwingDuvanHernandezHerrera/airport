package org.vuelosGlobales.fare.infrastructure;

import org.vuelosGlobales.fare.domain.Fare;

import java.util.List;
import java.util.Optional;

public interface FareRepository {
    void save(Fare fare);
    void update(Fare fare);
    Optional<Fare> findById(int id);
    List<Fare> findAll();
    void delete(int id);
}
