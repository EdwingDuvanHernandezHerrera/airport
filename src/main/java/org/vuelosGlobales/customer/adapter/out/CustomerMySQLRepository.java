package org.vuelosGlobales.customer.adapter.out;

import org.vuelosGlobales.customer.domain.Customer;
import org.vuelosGlobales.customer.domain.CustomerDocuDTO;
import org.vuelosGlobales.customer.infrastructure.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerMySQLRepository implements CustomerRepository {
    private final String url;
    private final String user;
    private final String password;

    public CustomerMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Customer customer) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO customer (name, lastName, nroIdc, age, idDocumentc) VALUES (?,?,?,?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, customer.getName());
                stm.setString(2, customer.getLastName());
                stm.setInt(3, customer.getNroId());
                stm.setInt(4, customer.getAge());
                stm.setInt(5, customer.getIdDocument());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE customer SET name = ?, lastName = ?, nroIdc = ?, age = ?, idDocumentc = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, customer.getName());
                stm.setString(2, customer.getLastName());
                stm.setInt(3, customer.getNroId());
                stm.setInt(4, customer.getAge());
                stm.setInt(5, customer.getIdDocument());
                stm.setInt(6, customer.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Customer> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM customer WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Customer obj = new Customer(resultSet.getInt("id"), resultSet.getString("name"),
                                resultSet.getString("lastName"), resultSet.getInt("nroIdc"),
                                resultSet.getInt("age"), resultSet.getInt("idDocumentc"));
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
    public List<Customer> findAll() {
        List<Customer> objects = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM customer";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Customer customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getString("lastName"), resultSet.getInt("nroIdc"),
                            resultSet.getInt("age"), resultSet.getInt("idDocumentc"));
                    objects.add(customer);
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
            String query = "DELETE FROM customer WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CustomerDocuDTO> getAllCustDoc() {
        List<CustomerDocuDTO> objects = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT c.id, c.name AS 'customer', c.lastName, c.nroIdc, c.age, d.name AS 'document' FROM customer c " +
                    "INNER JOIN documenttype d ON d.id = c.idDocumentc";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    CustomerDocuDTO customer = new CustomerDocuDTO(resultSet.getInt("id"), resultSet.getString("customer"),
                            resultSet.getString("lastName"), resultSet.getInt("nroIdc"),
                            resultSet.getInt("age"), resultSet.getString("document"));
                    objects.add(customer);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public Optional<CustomerDocuDTO> getCustDocById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT c.id, c.name AS 'customer', c.lastName, c.nroIdc, c.age, d.name AS 'document' FROM customer c " +
                    "INNER JOIN documenttype d ON d.id = c.idDocumentc WHERE c.id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                ResultSet resultSet = stm.executeQuery();
                if (resultSet.next()){
                    CustomerDocuDTO customer = new CustomerDocuDTO(resultSet.getInt("id"), resultSet.getString("customer"),
                            resultSet.getString("lastName"), resultSet.getInt("nroIdc"),
                            resultSet.getInt("age"), resultSet.getString("document"));
                   return Optional.of(customer);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
