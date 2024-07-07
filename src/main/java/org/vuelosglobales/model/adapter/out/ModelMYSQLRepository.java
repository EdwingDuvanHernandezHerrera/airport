package org.vuelosglobales.model.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.vuelosglobales.model.domain.Model;
import org.vuelosglobales.model.domain.ModelManufacturerDTO;
import org.vuelosglobales.model.infrastructure.ModelRepository;

public class ModelMYSQLRepository implements ModelRepository{  

    private final String url;
    private final String user;
    private final String password;

    public ModelMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }


    @Override
    public void save(Model model) {

        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO model(name, manufacturerId) VALUES (?,?)";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, model.getName());
                stm.setInt(2, model.getManufacturerId());
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }

    }

    @Override
    public void update(Model model) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE model SET name = ?, manufacturerId = ?  WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, model.getName());
                stm.setInt(2, model.getManufacturerId());
                stm.setInt(3, model.getId());
                stm.executeUpdate();
            }
        } catch (SQLException ignored) {
        } 
    }

    @Override
    public void delete(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM model WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }    
        } catch (SQLException ignored) {
        }
    }

    @Override
    public List<Model> findAll() {

        List<Model> models = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM model";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                try(ResultSet resultSet = stm.executeQuery()){
                    while(resultSet.next()){
                        Model model = new Model(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("manufacturerId")
                        );

                        models.add(model);
                    }
                }    
            }

        } catch (SQLException ignored) {
        }
        return models;

    }

    @Override
    public Optional<Model> findById(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM model WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if(resultSet.next()){
                        Model model = new Model(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("manufacturerId")
                        );
                        return Optional.of(model);                        
                    }
                }
            }       
        } catch (SQLException ignored) {}
        return Optional.empty();
    }

    @Override
    public List<ModelManufacturerDTO> findAllDTO() {

        List<ModelManufacturerDTO> modelsDTO = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT m.id, m.name, f.name AS 'fabricante' FROM model m INNER JOIN manufacturer f ON m.manufacturerId = f.id";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                try(ResultSet resultSet = stm.executeQuery()){
                    while(resultSet.next()){
                        ModelManufacturerDTO modelManufacturerDTO = new ModelManufacturerDTO(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("fabricante")
                        );
                        modelsDTO.add(modelManufacturerDTO);
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modelsDTO;

    }
}
