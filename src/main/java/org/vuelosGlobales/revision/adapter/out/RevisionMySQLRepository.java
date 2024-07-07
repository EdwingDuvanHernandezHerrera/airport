package org.vuelosGlobales.revision.adapter.out;

import org.vuelosGlobales.revision.domain.Revision;
import org.vuelosGlobales.revision.domain.RevisionInfoDTO;
import org.vuelosGlobales.revision.infrastructure.RevisionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RevisionMySQLRepository implements RevisionRepository {
    private final String url;
    private final String user;
    private final String password;

    public RevisionMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public int save(Revision revision) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO revision (revisionDate, idPlane, description) VALUES (?,?,?)";
            preparedStatement = conn.prepareStatement(query, preparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, revision.getRevisionDate());
            preparedStatement.setInt(2, revision.getIdPlane());
            preparedStatement.setString(3, revision.getDescription());
            int columnasAfectadas = preparedStatement.executeUpdate();
            if (columnasAfectadas > 0){
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    int idRevision = resultSet.getInt(1);
                    return idRevision;
                }else {
                    System.out.println("Algo salió mal");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void saveReviEmployee(int idRevision, String idEmployee){
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO revemployee (idRevision, idEmployee) VALUES(?,?)";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, idRevision);
                stm.setString(2, idEmployee);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Revision revision) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "UPDATE revision SET revisionDate = ?, idPlane = ?, description = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setDate(1, revision.getRevisionDate());
                stm.setInt(2, revision.getIdPlane());
                stm.setString(3, revision.getDescription());
                stm.setInt(4, revision.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Revision> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, revisionDate, idPlane, description FROM revision WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Revision obj = new Revision(resultSet.getInt("id"), resultSet.getDate("revisionDate"),
                                resultSet.getInt("idPlane"), resultSet.getString("description"));
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
    public List<Revision> findAll() {
        List<Revision> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, revisionDate, idPlane, description FROM revision";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Revision revision = new Revision(resultSet.getInt("id"), resultSet.getDate("revisionDate"),
                            resultSet.getInt("idPlane"), resultSet.getString("description"));
                    objects.add(revision);
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
            String query = "DELETE FROM revision WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Revision> findByIdPlane(int id) {
        List<Revision> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, revisionDate, idPlane, description FROM revision WHERE idPlane = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Revision revision = new Revision(resultSet.getInt("id"), resultSet.getDate("revisionDate"),
                            resultSet.getInt("idPlane"), resultSet.getString("description"));
                    objects.add(revision);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    @Override
    public List<RevisionInfoDTO> findRevisionByPlane(int id){
        List<RevisionInfoDTO> objects = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT r.id, r.revisionDate, e.name AS 'empleado', p.plates, m.name AS 'model', r.description FROM revision r " +
                    "INNER JOIN revemployee re ON re.idRevision = r.id " +
                    "INNER JOIN employee e ON  e.id = re.idEmployee " +
                    "INNER JOIN plane p ON p.id = r.idPlane " +
                    "INNER JOIN model m ON m.id = p.idModel " +
                    "WHERE p.id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                ResultSet resultSet = stm.executeQuery();
                while(resultSet.next()){
                    RevisionInfoDTO object = new RevisionInfoDTO(resultSet.getInt("id"), resultSet.getString("revisionDate"),
                            resultSet.getString("empleado"), resultSet.getString("plates"), resultSet.getString("model"),
                            resultSet.getString("description"));
                    objects.add(object);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }
}
