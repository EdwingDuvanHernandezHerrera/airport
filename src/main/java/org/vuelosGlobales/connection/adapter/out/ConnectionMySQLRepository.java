package org.vuelosGlobales.connection.adapter.out;

import org.vuelosGlobales.connection.domain.ConnInfoDTO;
import org.vuelosGlobales.connection.domain.Connections;
import org.vuelosGlobales.connection.infrastructure.ConnectionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConnectionMySQLRepository implements ConnectionRepository {
    private final String url;
    private final String user;
    private final String password;

    public ConnectionMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Connections connections) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO flightconnection (connectionNumber, idTrip, idPlane, idAirport) VALUES (?,?,?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, connections.getConnectionNumber());
                stm.setInt(2, connections.getIdTrip());
                stm.setInt(3, connections.getIdPlane());
                stm.setString(4, connections.getIdAriport());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Connections connections) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE flightconnection SET connectionNumber = ?, idTrip = ?, idPlane = ?, idAirport = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, connections.getConnectionNumber());
                stm.setInt(2, connections.getIdTrip());
                stm.setInt(3, connections.getIdPlane());
                stm.setString(4, connections.getIdAriport());
                stm.setInt(5, connections.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Connections> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, connectionNumber, idTrip, idPlane, idAirport FROM flightconnection WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Connections obj = new Connections(resultSet.getInt("id"), resultSet.getString("connectionNumber"),
                                resultSet.getInt("idTrip"), resultSet.getInt("idPlane"), resultSet.getString("idAirport"));
                        return Optional.of(obj);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Connections> findAll() {
        List<Connections> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, connectionNumber, idTrip, idPlane, idAirplane FROM flightconnection";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Connections connections = new Connections(resultSet.getInt("id"), resultSet.getString("connectionNumber"),
                            resultSet.getInt("idTrip"), resultSet.getInt("idPlane"), resultSet.getString("idAirplane"));
                    objects.add(connections);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }


    @Override
    public void delete(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM flightconnection WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ConnInfoDTO> findAllConnByTrip(int id) {
        List<ConnInfoDTO> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT t.id AS 'idTrip', c.id AS 'idConn', c.connectionNumber, p.plates, CONCAT_WS(' - ', ci.name, a.name) AS 'cityA' FROM flightconnection c " +
                    "INNER JOIN trip t ON t.id = c.idTrip " +
                    "INNER JOIN plane p ON p.id = c.idPlane " +
                    "INNER JOIN airport a ON a.id = c.idAirport " +
                    "INNER JOIN city ci ON ci.id = a.idCity " +
                    "WHERE t.id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    ConnInfoDTO connections = new ConnInfoDTO(resultSet.getInt("idTrip"), resultSet.getInt("idConn"),
                            resultSet.getString("connectionNumber"), resultSet.getString("plates"),
                            resultSet.getString("cityA"));
                    objects.add(connections);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public Optional<ConnInfoDTO> findConnByTripByIdConn(int idConn) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT t.id AS 'idTrip', c.id AS 'idConn', c.connectionNumber, p.plates, CONCAT_WS(' - ', ci.name, a.name) AS 'cityA' FROM flightconnection c" +
                    "INNER JOIN plane p ON p.id = c.idPlane " +
                    "INNER JOIN airport a ON a.id = c.idAirport " +
                    "INNER JOIN city ci ON ci.id = a.idCity " +
                    "WHERE t.id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, idConn);
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    ConnInfoDTO connections = new ConnInfoDTO(resultSet.getInt("idTrip"), resultSet.getInt("idConn"),
                            resultSet.getString("connectionNumber"), resultSet.getString("plates"),
                            resultSet.getString("cityA"));
                    return Optional.of(connections);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Connections> validateNroConn(int nroConn) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT connectionNumber FROM flightconnection WHERE connectionNumber = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, nroConn);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Connections obj = new Connections();
                        obj.setConnectionNumber(resultSet.getString("flightconnection"));
                        return Optional.of(obj);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ConnInfoDTO> findConnById(int id) {
        return Optional.empty();
    }
}
