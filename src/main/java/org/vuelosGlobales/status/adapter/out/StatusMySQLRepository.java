package org.vuelosGlobales.status.adapter.out;

import org.vuelosGlobales.status.domain.Status;
import org.vuelosGlobales.status.infrastructure.StatusRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusMySQLRepository implements StatusRepository {
    private final String url;
    private final String user;
    private final String password;

    public StatusMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Status status) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO statusA (name) VALUES (?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, status.getName());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Status status) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE statusA SET name = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, status.getName());
                stm.setInt(2, status.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Status> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name FROM statusA WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Status obj = new Status(resultSet.getInt("id"), resultSet.getString("name"));
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
    public List<Status> findAll() {
        List<Status> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name FROM statusA";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Status status = new Status(resultSet.getInt("id"), resultSet.getString("name"));
                    objects.add(status);
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
            String query = "DELETE FROM statusA WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
