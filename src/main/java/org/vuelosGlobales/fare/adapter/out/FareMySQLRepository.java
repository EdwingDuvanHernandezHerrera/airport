package org.vuelosGlobales.fare.adapter.out;

import org.vuelosGlobales.fare.domain.Fare;
import org.vuelosGlobales.fare.infrastructure.FareRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FareMySQLRepository implements FareRepository {
    
    private final String url;
    private final String user;
    private final String password;

    public FareMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Fare fare) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO flightfare (description, details, value) VALUES (?, ?, ?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, fare.getDescription());
                stm.setString(2, fare.getDetails());
                stm.setDouble(3, fare.getValue());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Fare fare) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE flightfare SET description = ?, details = ?, value = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, fare.getDescription());
                stm.setString(2, fare.getDetails());
                stm.setDouble(3, fare.getValue());
                stm.setInt(4, fare.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Fare> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, description, details, value FROM flightfare WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Fare obj = new Fare(resultSet.getInt("id"), resultSet.getString("description"),
                                resultSet.getString("details"), resultSet.getDouble("value"));
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
    public List<Fare> findAll() {
        List<Fare> objects = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, description, details, value FROM flightfare";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Fare fare = new Fare(resultSet.getInt("id"), resultSet.getString("description"),
                            resultSet.getString("details"), resultSet.getDouble("value"));
                    objects.add(fare);
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
            String query = "DELETE FROM flightfare WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
