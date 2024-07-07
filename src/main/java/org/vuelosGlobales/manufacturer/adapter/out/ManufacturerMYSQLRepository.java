package org.vuelosGlobales.manufacturer.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.vuelosGlobales.manufacturer.domain.Manufacturer;
import org.vuelosGlobales.manufacturer.infrastructure.ManufacturerRepository;

public class ManufacturerMYSQLRepository implements ManufacturerRepository{  

    private final String url;
    private final String user;
    private final String password;

    public ManufacturerMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    @Override
    public void save(Manufacturer manufacturer) {

        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO manufacturer(name) VALUES (?)";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, manufacturer.getName());
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error 404");
        }

    }

    @Override
    public void update(Manufacturer manufacturer) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE manufacturer SET name = ? WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, manufacturer.getName());
                stm.setInt(2, manufacturer.getId());
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
        } 
    }

    @Override
    public void delete(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM manufacturer WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }    
        } catch (SQLException ex) {
        }
    }

    @Override
    public List<Manufacturer> findAll() {

        List<Manufacturer> manufacturers = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM manufacturer";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                try(ResultSet resultSet = stm.executeQuery()){
                    while(resultSet.next()){
                        Manufacturer manufacturer = new Manufacturer(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );

                        manufacturers.add(manufacturer);
                    }
                }    
            }

        } catch (SQLException ex) {
        }
        return manufacturers;

    }

    @Override
    public Optional<Manufacturer> findById(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM manufacturer WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if(resultSet.next()){
                        Manufacturer manufacturer = new Manufacturer(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        return Optional.of(manufacturer);                        
                    }
                }
            }       
        } catch (SQLException ex) {}
        return Optional.empty();
    }
}
