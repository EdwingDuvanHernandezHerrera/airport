package org.vuelosGlobales.flightRes.adapter.out;

import org.vuelosGlobales.flightRes.domain.FlightRes;
import org.vuelosGlobales.flightRes.domain.ReservationByCustomer;
import org.vuelosGlobales.flightRes.domain.Ticket;
import org.vuelosGlobales.flightRes.infrastructure.FlightResRepository;
import org.vuelosGlobales.helpers.Constants;
import org.vuelosGlobales.fare.adapter.out.FareMySQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightResMySQLRepository implements FlightResRepository {
    private final String url;
    private final String user;
    private final String password;

    public FlightResMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public int save(FlightRes flightRes) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO tripbooking (date, idTrip) VALUES (CURRENT_DATE,?)";
            preparedStatement = conn.prepareStatement(query, preparedStatement.RETURN_GENERATED_KEYS);
//                preparedStatement.setString(1, flightRes.getDate());
                preparedStatement.setInt(1, flightRes.getIdTrip());
                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0){
                    resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()){
                        int idGenerate = resultSet.getInt(1);
                            return idGenerate;
                    }
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int saveDetailTripbooking(int idTripbooking, int idCustomer, int idFare, String status){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            String query = "INSERT INTO tripbookingdetail (idTripBooking, idCustomers, idFares, status) VALUES (?,?,?,?)";
            preparedStatement = conn.prepareStatement(query, preparedStatement.RETURN_GENERATED_KEYS);
//                preparedStatement.setString(1, flightRes.getDate());
            preparedStatement.setInt(1, idTripbooking);
            preparedStatement.setInt(2, idCustomer);
            preparedStatement.setInt(3, idFare);
            preparedStatement.setString(4, status);
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0){
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    int idGenerate = resultSet.getInt(1);
                    return idGenerate;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void update(FlightRes flightRes) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            FareMySQLRepository fareOut = new FareMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
            String query = "UPDATE tripbooking SET date = CURRENT_DATE, idTrip = ? WHERE id = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setString(1, flightRes.getDate());
                stm.setInt(2, flightRes.getIdTrip());
                stm.setInt(3, flightRes.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<FlightRes> findById(int id) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, date, idTrip FROM tripbooking WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        FlightRes obj = new FlightRes(resultSet.getInt("id"), resultSet.getString("date"),
                                resultSet.getInt("idTrip"));
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
    public List<FlightRes> findAll() {
        List<FlightRes> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT id, date, idTrip FROM tripbooking";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    FlightRes flightRes = new FlightRes(resultSet.getInt("id"), resultSet.getString("date"),
                            resultSet.getInt("idTrip"));
                    objects.add(flightRes);
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
            String query = "DELETE FROM tripbooking WHERE id = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findPlaneSeats(int idTrip) {
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT p.capacity FROM tripbooking tb " +
                    "INNER JOIN trip t ON t.id = tb.idTrip " +
                    "INNER JOIN flightconnection fc ON fc.idTrip = t.id " +
                    "INNER JOIN plane p ON p.id = fc.idPlane " +
                    "WHERE fc.idAirport = t.idDestination AND t.id = ?;";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, idTrip);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        return resultSet.getInt("capacity");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Integer> findReservedSeats(int idTrip) {
        List<Integer> seats = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT p.seat FROM tripbooking tb " +
                    "INNER JOIN tripbookingdetail tbd ON tb.id = tbd.idTripBooking " +
                    "INNER JOIN passenger p ON p.idTripBookingDetails = tbd.id " +
                    "WHERE tb.idTrip = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, idTrip);
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    seats.add(resultSet.getInt("seat"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seats;
    }

    @Override
    public List<Ticket> findTicket(int idTripBooking) {
        List<Ticket> tickets = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT tbd.idTripBooking AS 'reservation', p.name, p.lastName, p.nroId AS 'nrpPass', p.seat, ff.description, ff.value FROM passenger p " +
                    "INNER JOIN tripbookingdetail tbd ON tbd.id = p.idTripBookingDetails " +
                    "INNER JOIN flightfare ff ON ff.id = tbd.idFares " +
                    "WHERE tbd.idTripBooking = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, idTripBooking);
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Ticket ticket = new Ticket(resultSet.getInt("reservation"), resultSet.getString("name"),
                            resultSet.getString("lastName"), resultSet.getInt("nrpPass"), resultSet.getInt("seat"),
                            resultSet.getString("description"), resultSet.getDouble("value"));
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

    @Override
    public List<ReservationByCustomer> reservationByCustomers(int idCustomer){
        List<ReservationByCustomer> objects= new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT tb.id AS 'id_reservacion', t.tripDate, t.priceTrip, CONCAT_WS(' - ', co.name, ao.name) as 'origen', CONCAT_WS(' - ', cd.name, ad.name) as 'destino' FROM tripbookingdetail tbd " +
                    "INNER JOIN tripbooking tb ON tb.id = tbd.idTripBooking " +
                    "INNER JOIN trip t ON t.id = tb.idTrip " +
                    "INNER JOIN airport ao on ao.id = t.idOrigin " +
                    "INNER JOIN city co on co.id = ao.idCity " +
                    "INNER JOIN airport ad on ad.id = t.idDestination " +
                    "INNER JOIN city cd on cd.id = ad.idCity " +
                    "WHERE tbd.idCustomers = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, idCustomer);
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    ReservationByCustomer flightRes = new ReservationByCustomer(resultSet.getInt("id_reservacion"),
                            resultSet.getString("tripDate"),
                            resultSet.getDouble("priceTrip"), resultSet.getString("origen"),
                            resultSet.getString("destino"));
                    objects.add(flightRes);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    public Optional<ReservationByCustomer> reservation(int idReservation){
        try(Connection conn = DriverManager.getConnection(url, user, password)){
            String query = "SELECT tb.id AS 'id_reservacion', t.tripDate, t.priceTrip, CONCAT_WS(' - ', co.name, ao.name) as 'origen', CONCAT_WS(' - ', cd.name, ad.name) as 'destino' FROM tripbookingdetail tbd " +
                    "INNER JOIN tripbooking tb ON tb.id = tbd.idTripBooking " +
                    "INNER JOIN trip t ON t.id = tb.idTrip " +
                    "INNER JOIN airport ao on ao.id = t.idOrigin " +
                    "INNER JOIN city co on co.id = ao.idCity " +
                    "INNER JOIN airport ad on ad.id = t.idDestination " +
                    "INNER JOIN city cd on cd.id = ad.idCity " +
                    "WHERE tbd.idTripBooking = ?";
            try(PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, idReservation);
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    ReservationByCustomer flightRes = new ReservationByCustomer(resultSet.getInt("id_reservacion"),
                            resultSet.getString("tripDate"),
                            resultSet.getDouble("priceTrip"), resultSet.getString("origen"),
                            resultSet.getString("destino"));
                    return Optional.of(flightRes);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
    @Override
    public void updateReservation(int idReservation) {
        try (Connection conn = DriverManager.getConnection(url,user, password)){
            FareMySQLRepository fareOut = new FareMySQLRepository(Constants.URL, Constants.USER, Constants.PASSWORD);
            String query = "UPDATE tripbookingdetail SET status = 'cancelled' WHERE idTripBooking = ?";
            try (PreparedStatement stm = conn.prepareStatement(query)){
                stm.setInt(1, idReservation);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
