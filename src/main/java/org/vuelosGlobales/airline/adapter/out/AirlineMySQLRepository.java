package org.vuelosGlobales.airline.adapter.out;

import org.vuelosGlobales.airline.domain.Airline;
import org.vuelosGlobales.airline.infrastructure.AirlineRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirlineMySQLRepository implements AirlineRepository {
    private final String url;
    private final String user;
    private final String password;

    public AirlineMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Airline airline) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO airline (name) VALUES (?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, airline.getName());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Airline airline) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE airline SET name = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, airline.getName());
                stm.setInt(2, airline.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Airline> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name FROM airline WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Airline obj = new Airline(resultSet.getInt("id"), resultSet.getString("name"));
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
    public List<Airline> findAll() {
        List<Airline> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name FROM airline";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Airline airline = new Airline(resultSet.getInt("id"), resultSet.getString("name"));
                    objects.add(airline);
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
            String query = "DELETE FROM airline WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
