package org.vuelosGlobales.connection.application;

import org.vuelosGlobales.connection.domain.Connections;
import org.vuelosGlobales.connection.infrastructure.ConnectionRepository;

import java.util.List;
import java.util.Optional;

public class ConnectionService {
    private final ConnectionRepository connectionRepository;

    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public void createConnections(Connections connections){
        this.connectionRepository.save(connections);
    }

    public void updateConnections(Connections connections){
        this.connectionRepository.update(connections);
    }

    public Optional<Connections> getConnectionsById(int id){
        return this.connectionRepository.findById(id);
    }

    public List<Connections> getAllConnectionss(){
        return this.connectionRepository.findAll();
    }

    public void deleteConnections(int id){
        this.connectionRepository.delete(id);
    }
}
