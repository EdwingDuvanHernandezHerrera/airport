package org.vuelosGlobales.plane.adapter.out;

import org.vuelosGlobales.plane.domain.Plane;
import org.vuelosGlobales.plane.domain.PlaneStMdDTO;
import org.vuelosGlobales.plane.infrastructure.PlaneRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaneMySQLRepository implements PlaneRepository {
    private final String url;
    private final String user;
    private final String password;

    public PlaneMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Plane plane) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO plane (plates, capacity, fabricationDate, idAirline, idStatus, idModel) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, plane.getPlates());
                stm.setInt(2, plane.getCapacity());
                stm.setDate(3, plane.getFabricationDate());
                stm.setInt(4, plane.getIdAirline());
                stm.setInt(5, plane.getIdStatus());
                stm.setInt(6, plane.getIdModel());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Plane plane) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE plane SET plates = ?, capacity = ?, fabricationDate = ?, idAirline = ?, idStatus = ?, idModel = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, plane.getPlates());
                stm.setInt(2, plane.getCapacity());
                stm.setDate(3, plane.getFabricationDate());
                stm.setInt(4, plane.getIdAirline());
                stm.setInt(5, plane.getIdStatus());
                stm.setInt(6, plane.getIdModel());
                stm.setInt(7, plane.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Plane> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, plates, capacity, fabricationDate, idAirline, idStatus, idModel FROM plane WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Plane obj = new Plane(resultSet.getInt("id"), resultSet.getString("plates"),
                                resultSet.getInt("capacity"), resultSet.getDate("fabricationDate"),
                                resultSet.getInt("idAirline"), resultSet.getInt("idStatus"),
                                resultSet.getInt("idModel"));
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
    public Optional<Plane> validatePlate(String plate){
        try (Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT plates FROM plane WHERE plates = ? ";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, plate);
                ResultSet resultSet = stm.executeQuery();
                if (resultSet.next()){
                    Plane plane = new Plane();
                    plane.setPlates(resultSet.getString("plates"));
                    return Optional.of(plane);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Plane> findAll() {
        List<Plane> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, plates, capacity, fabricationDate, idAirline, idStatus, idModel FROM plane";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Plane plane = new Plane(resultSet.getInt("id"), resultSet.getString("plates"),
                            resultSet.getInt("capacity"), resultSet.getDate("fabricationDate"),
                            resultSet.getInt("idAirline"), resultSet.getInt("idStatus"),
                            resultSet.getInt("idModel"));
                    objects.add(plane);
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
            String query = "DELETE FROM plane WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlaneStMdDTO> findAllPlaneStMd(boolean filter, int id) {
        List<PlaneStMdDTO> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT p.id, p.plates, p.capacity, p.fabricationDate, ai.name AS 'airline', s.name AS 'status', m.name AS 'model' FROM plane p INNER JOIN statusA s ON  s.id = p.idStatus INNER JOIN model m ON m.id = p.idModel INNER JOIN airline ai ON ai.id = p.idAirline";
            if (filter){
                query += " WHERE p.idAirline = ?";
            }
            try(PreparedStatement stm = conn.prepareStatement(query)){
                if (filter){
                    stm.setInt(1, id);
                }
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    PlaneStMdDTO plane = new PlaneStMdDTO(resultSet.getInt("id"), resultSet.getString("plates"),
                            resultSet.getInt("capacity"), resultSet.getDate("fabricationDate"), resultSet.getString("airline"),
                            resultSet.getString("status"), resultSet.getString("model"));
                    objects.add(plane);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public Optional<PlaneStMdDTO> findPlaneStMdById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT p.id, p.plates, p.capacity, p.fabricationDate, ai.name AS 'airline', s.name AS 'status', m.name AS 'model' FROM plane p INNER JOIN statusA s ON  s.id = p.idStatus INNER JOIN model m ON m.id = p.idModel INNER JOIN airline ai ON ai.id = p.idAirline WHERE p.id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                ResultSet resultSet = stm.executeQuery();
                if (resultSet.next()){
                    PlaneStMdDTO plane = new PlaneStMdDTO(resultSet.getInt("id"), resultSet.getString("plates"),
                            resultSet.getInt("capacity"), resultSet.getDate("fabricationDate"), resultSet.getString("airline"),
                            resultSet.getString("status"), resultSet.getString("model"));
                   return Optional.of(plane);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
