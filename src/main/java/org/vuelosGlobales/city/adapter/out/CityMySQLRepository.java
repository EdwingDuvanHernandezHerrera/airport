package org.vuelosGlobales.city.adapter.out;

import org.vuelosGlobales.city.domain.CityCountryDTO;
import org.vuelosGlobales.city.infrastructure.CityRepository;
import org.vuelosGlobales.city.domain.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityMySQLRepository implements CityRepository {

    private final String url;
    private final String user;
    private final String password;

    public CityMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(City city) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO city (id, name, idCountry) VALUES (?,?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, city.getId());
                stm.setString(2, city.getName());
                stm.setString(3, city.getIdCountry());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(City city) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE city SET name = ?, idCountry = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, city.getName());
                stm.setString(2, city.getIdCountry());
                stm.setString(3, city.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<City> findById(String id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, idCountry FROM city WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        City c = new City(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("idCountry"));
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
    public List<City> findAll() {
        List<City> objects = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, name, idCountry FROM city";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    City city = new City(resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("idCountry"));
                    objects.add(city);
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
            String query = "DELETE FROM city WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CityCountryDTO> cityWithCountry() {
        List<CityCountryDTO> objects = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT c.id AS 'idCity', c.name AS 'Ciudad', p.name AS 'Pais' FROM city c INNER JOIN country p ON p.id = c.idCountry";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    CityCountryDTO cityCountryDTO = new CityCountryDTO(resultSet.getString("idCity"), resultSet.getString("Ciudad"), resultSet.getString("Pais"));
                    objects.add(cityCountryDTO);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }
}
