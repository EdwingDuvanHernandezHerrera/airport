package org.vuelosGlobales.tripcrew.adapter.out;

import com.sun.source.tree.TryTree;
import org.vuelosGlobales.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.tripcrew.domain.TripCrew;
import org.vuelosGlobales.tripcrew.domain.TripCrewInfoDTO;
import org.vuelosGlobales.tripcrew.infrastructure.TripCrewRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripCrewMySQLRepository implements TripCrewRepository {
    private final String url;
    private final String user;
    private final String password;

    public TripCrewMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(TripCrew tripCrew) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO tripcrew (idEmployees, idConection) VALUES (?, ?)";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, tripCrew.getIdEmployee());
                stm.setInt(2, tripCrew.getIdConnection());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TripCrewInfoDTO> showCrewByConn(int idConn) {
        List<TripCrewInfoDTO> objects = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT e.name AS 'emp', tr.name AS 'rol', c.connectionNumber, CONCAT_WS(' - ', ci.name, a.name) AS 'airport' FROM employee e " +
                    "INNER JOIN tripulationroles tr ON tr.id = e.idRol " +
                    "INNER JOIN tripcrew tc ON e.id = tc.idEmployees " +
                    "INNER JOIN flightconnection c ON tc.idConection = c.id " +
                    "INNER JOIN airport a ON c.idAirport = a.id " +
                    "INNER JOIN city ci ON ci.id = a.idCity WHERE c.id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, idConn);
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    TripCrewInfoDTO object = new TripCrewInfoDTO(resultSet.getString("emp"), resultSet.getString("rol"),
                            resultSet.getString("connectionNumber"), resultSet.getString("airport"));
                    objects.add(object);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }
}
