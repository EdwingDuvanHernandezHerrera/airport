package org.vuelosGlobales.country.adapter.out;

import org.vuelosGlobales.country.domain.Country;
import org.vuelosGlobales.country.infrastructure.CountryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryMySQLRepository implements CountryRepository {
    private final String url;
    private final String user;
    private final String password;

    public CountryMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Country country) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO country (id, name) VALUES (?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, country.getId());
                stm.setString(2, country.getName());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Country country) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE country SET name = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, country.getName());
                stm.setString(2, country.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Country> findById(String id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name FROM country WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Country c = new Country(resultSet.getString("id"), resultSet.getString("name"));
                        return Optional.of(c);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name FROM country";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Country country = new Country(resultSet.getString("id"), resultSet.getString("name"));
                    countries.add(country);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countries;
        }

        @Override
    public void delete(String id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM country WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
