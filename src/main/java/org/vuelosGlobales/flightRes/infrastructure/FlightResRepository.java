package org.vuelosGlobales.flightRes.infrastructure;

import org.vuelosGlobales.flightRes.domain.FlightRes;
import org.vuelosGlobales.flightRes.domain.ReservationByCustomer;
import org.vuelosGlobales.flightRes.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface FlightResRepository {
    int save(FlightRes flightRes);
    void update(FlightRes flightRes);
    Optional<FlightRes> findById(int id);
    List<FlightRes> findAll();
    void delete(int id);

    int findPlaneSeats(int idTrip);
    int saveDetailTripbooking(int idTripbooking, int idCustomer, int idFare, String status);
    List<Integer> findReservedSeats(int idTrip);
    List<Ticket> findTicket(int idTripBooking);
    List<ReservationByCustomer> reservationByCustomers(int idCustomer);
    Optional<ReservationByCustomer> reservation(int idReservation);
    void updateReservation(int idReservation);
}
