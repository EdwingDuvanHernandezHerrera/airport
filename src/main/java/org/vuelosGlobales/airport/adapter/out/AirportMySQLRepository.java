package org.vuelosGlobales.airport.adapter.out;

import org.vuelosGlobales.airport.domain.Airport;
import org.vuelosGlobales.airport.domain.AirportCityDTO;
import org.vuelosGlobales.airport.infrastructure.AirportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirportMySQLRepository implements AirportRepository {
    private final String url;
    private final String user;
    private final String password;

    public AirportMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Airport airport) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO airport (id, name, idCity) VALUES (?,?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, airport.getId());
                stm.setString(2, airport.getName());
                stm.setString(3, airport.getIdCity());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Airport airport) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE airport SET name = ?, idCity = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, airport.getName());
                stm.setString(2, airport.getIdCity());
                stm.setString(3, airport.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Airport> findById(String id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, idCity FROM airport WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Airport obj = new Airport(resultSet.getString("id"), resultSet.getString("name"),
                                resultSet.getString("idCity"));
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
    public Optional<AirportCityDTO> findAirportCityById(String id){
        try (Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT a.id, a.name AS 'airport', c.name AS 'city' FROM airport a INNER JOIN city c on c.id = a.idCity WHERE a.id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, id);
                ResultSet resultSet = stm.executeQuery();
                if (resultSet.next()){
                    AirportCityDTO obj = new AirportCityDTO(resultSet.getString("id"), resultSet.getString("airport"),
                            resultSet.getString("city"));
                    return Optional.of(obj);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Airport> findAll() {
        List<Airport> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, idCity FROM airport";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Airport airport = new Airport(resultSet.getString("id"), resultSet.getString("name"),
                            resultSet.getString("idCity"));
                    objects.add(airport);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public List<AirportCityDTO> findAllAirportCity() {
        List<AirportCityDTO> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT a.id, a.name AS 'airport', c.name AS 'city' FROM airport a INNER JOIN city c on c.id = a.idCity";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    AirportCityDTO airport = new AirportCityDTO(resultSet.getString("id"), resultSet.getString("airport"),
                            resultSet.getString("city"));
                    objects.add(airport);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public void delete(String id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM airport WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
