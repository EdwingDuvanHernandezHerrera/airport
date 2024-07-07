package org.vuelosGlobales.employee.adapter.out;

import org.vuelosGlobales.employee.domain.Employee;
import org.vuelosGlobales.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.employee.infrastructure.EmployeeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeMySQLRepository implements EmployeeRepository {

    private final String url;
    private final String user;
    private final String password;


    public EmployeeMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Employee employee) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO employee (id, name, ingressDate, idRol, idAirline, idAirport) VALUES (?,?,?,?,?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, employee.getId());
                stm.setString(2, employee.getNombre());
                stm.setString(3, employee.getIngressDate());
                stm.setInt(4, employee.getIdRol());
                stm.setInt(5, employee.getIdAirline());
                stm.setString(6, employee.getIdAirport());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Employee employee) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE employee SET name = ?, ingressDate = ?, idRol = ?, idAirline = ?, idAirport = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, employee.getNombre());
                stm.setString(2, employee.getIngressDate());
                stm.setInt(3, employee.getIdRol());
                stm.setInt(4, employee.getIdAirline());
                stm.setString(5, employee.getIdAirport());
                stm.setString(6, employee.getId());
                stm.executeUpdate();
                System.out.println("here");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Employee> findById(String id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, ingressDate, idRol, idAirline, idAirport FROM employee WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Employee obj = new Employee(resultSet.getString("id"), resultSet.getString("name"),
                                resultSet.getString("ingressDAte"), resultSet.getInt("idRol"),
                                resultSet.getInt("idAirline"), resultSet.getString("idAirport"));
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
    public List<Employee> findAll() {
        List<Employee> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, ingressDate, idRol, idAirline, idAirport FROM employee";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Employee employee = new Employee(resultSet.getString("id"), resultSet.getString("name"),
                            resultSet.getString("ingressDAte"), resultSet.getInt("idRol"),
                            resultSet.getInt("idAirline"), resultSet.getString("idAirport"));
                    objects.add(employee);
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
            String query = "DELETE FROM employee WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EmployeeRelationshipDTO> findAllEmployeesInfo(boolean filter, int id) {
        List<EmployeeRelationshipDTO> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT e.id, e.name, e.ingressDate, tr.name AS 'role', al.name AS 'airline', CONCAT_WS(' - ', c.name, a.name) AS 'airport' from employee e " +
                    "INNER JOIN tripulationroles tr ON tr.id = e.idRol " +
                    "INNER JOIN airline al ON al.id = e.idAirline " +
                    "INNER JOIN airport a ON a.id = e.idAirport " +
                    "INNER JOIN city c ON c.id = a.idCity";
            if (filter){
                query += " WHERE e.idAirline = ?";
            }
            try(PreparedStatement stm = conn.prepareStatement(query)){
                if (filter){
                    stm.setInt(1, id);
                }
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    EmployeeRelationshipDTO employee = new EmployeeRelationshipDTO(resultSet.getString("id"), resultSet.getString("name"),
                            resultSet.getString("ingressDAte"), resultSet.getString("role"),
                            resultSet.getString("airline"), resultSet.getString("airport"));
                    objects.add(employee);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public Optional<EmployeeRelationshipDTO> findEmployeeInfoById(String id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT e.id, e.name, e.ingressDate, tr.name AS 'role', al.name AS 'airline', CONCAT_WS(' - ', c.name, a.name) AS 'airport' from employee e " +
                    "INNER JOIN tripulationroles tr ON tr.id = e.idRol " +
                    "INNER JOIN airline al ON al.id = e.idAirline " +
                    "INNER JOIN airport a ON a.id = e.idAirport " +
                    "INNER JOIN city c ON c.id = a.idCity WHERE e.id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        EmployeeRelationshipDTO obj = new EmployeeRelationshipDTO(resultSet.getString("id"), resultSet.getString("name"),
                                resultSet.getString("ingressDAte"), resultSet.getString("role"),
                                resultSet.getString("airline"), resultSet.getString("airport"));
                        return Optional.of(obj);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
