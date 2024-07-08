package org.vuelosGlobales.passenger.adapter.out;

import org.vuelosGlobales.passenger.infrastructure.PassengerRepository;
import org.vuelosGlobales.passenger.domain.Passenger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PassengerMySQLRepo implements PassengerRepository {
    private final String url;
    private final String user;
    private final String password;

    public PassengerMySQLRepo(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Passenger passenger) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO passenger (name, lastName, nroId, age, seat, idDocument, idTripBookingDetails) VALUES (?,?,?,?,?,?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, passenger.getName());
                stm.setString(2, passenger.getLastName());
                stm.setInt(3, passenger.getNroId());
                stm.setInt(4, passenger.getAge());
                stm.setInt(5, passenger.getSeat());
                stm.setInt(6, passenger.getIdDocument());
                stm.setInt(7, passenger.getIdTripBookingDetails());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Passenger passenger) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE passenger SET name = ?, lastName = ?, nroId = ?, age = ?, seat = ?, idDocument = ?, idTripBookingDetails = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, passenger.getName());
                stm.setString(2, passenger.getLastName());
                stm.setInt(3, passenger.getNroId());
                stm.setInt(4, passenger.getAge());
                stm.setInt(5, passenger.getSeat());
                stm.setInt(6, passenger.getIdDocument());
                stm.setInt(7, passenger.getIdTripBookingDetails());
                stm.setInt(8, passenger.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Passenger> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, lastName, nroId, age, seat, idDocument, idTripBookingDetails FROM passenger WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Passenger obj = new Passenger(resultSet.getInt("id"), resultSet.getString("name"),
                                resultSet.getString("lastName"), resultSet.getInt("nroId"),
                                resultSet.getInt("age"), resultSet.getInt("seat"), resultSet.getInt("idDocument"),
                                resultSet.getInt("idTripBookingDetails"));
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
    public List<Passenger> findAll() {
        List<Passenger> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, lastName, nroId, age, seat, idDocument, idTripBookingDetails FROM passenger";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Passenger passenger = new Passenger(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getString("lastName"), resultSet.getInt("nroId"),
                            resultSet.getInt("age"), resultSet.getInt("seat"), resultSet.getInt("idDocument"),
                            resultSet.getInt("idTripBookingDetails"));
                    objects.add(passenger);
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
            String query = "DELETE FROM passenger WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
