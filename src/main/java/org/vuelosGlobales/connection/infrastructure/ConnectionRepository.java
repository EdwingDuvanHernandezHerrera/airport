package org.vuelosGlobales.connection.infrastructure;

import org.vuelosGlobales.connection.domain.ConnInfoDTO;
import org.vuelosGlobales.connection.domain.Connections;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository {
    void save(Connections connections);
    void update(Connections connections);
    Optional<Connections> findById(int id);
    List<Connections> findAll();
    void delete(int id);
    List<ConnInfoDTO> findAllConnByTrip(int id);
    Optional<ConnInfoDTO> findConnById(int id);
    Optional<ConnInfoDTO> findConnByTripByIdConn(int idConn);
    Optional<Connections> validateNroConn(int nroConn);
}
