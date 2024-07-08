package org.vuelosGlobales.flightRes.application;

import org.vuelosGlobales.passenger.domain.Passenger;
import org.vuelosGlobales.passenger.infrastructure.PassengerRepository;
import org.vuelosGlobales.trip.domain.TripAirportDTO;
import org.vuelosGlobales.trip.infrastructure.TripRepository;
import org.vuelosGlobales.customer.domain.Customer;
import org.vuelosGlobales.customer.infrastructure.CustomerRepository;
import org.vuelosGlobales.flightRes.domain.FlightRes;
import org.vuelosGlobales.flightRes.domain.ReservationByCustomer;
import org.vuelosGlobales.flightRes.domain.Ticket;
import org.vuelosGlobales.flightRes.infrastructure.FlightResRepository;
import org.vuelosGlobales.document.domain.Document;
import org.vuelosGlobales.document.infrastructure.DocumentRepository;
import org.vuelosGlobales.fare.domain.Fare;
import org.vuelosGlobales.fare.infrastructure.FareRepository;
import java.util.List;
import java.util.Optional;

public class FlightResService {
    private final FlightResRepository flightResRepository;
    private final CustomerRepository customerRepository;
    private final TripRepository tripRepository;
    private final FareRepository fareRepository;
    private final PassengerRepository passengerRepository;
    private final DocumentRepository documentRepository;

    public FlightResService(FlightResRepository flightResRepository, CustomerRepository customerRepository, TripRepository tripRepository, FareRepository fareRepository, PassengerRepository passengerRepository, DocumentRepository documentRepository) {
        this.flightResRepository = flightResRepository;
        this.customerRepository = customerRepository;
        this.tripRepository = tripRepository;
        this.fareRepository = fareRepository;
        this.passengerRepository = passengerRepository;
        this.documentRepository = documentRepository;
    }

    //Mostrar todos los vuelos
    public List<TripAirportDTO> showAllTrips(){
        return this.tripRepository.findAllTripAirport();
    }
    // Obtener un solo vuelo
    public Optional<TripAirportDTO> showOneTrip(int idTrip){
        return this.tripRepository.findTripAirportById(idTrip);
    }

    public int createFlightBooking(FlightRes reservation){
        return this.flightResRepository.save(reservation);
    }

    public Optional<FlightRes> showOneFlightBooking(int idReserva){
        return this.flightResRepository.findById(idReserva);
    }

    public List<Customer> showAllCustomers(){
        return this.customerRepository.findAll();
    }

    public Optional<Customer> showOneCustomer(int idCustomer){
        return this.customerRepository.findById(idCustomer);
    }

    public List<Fare> showAllFares(){
        return this.fareRepository.findAll();
    }

    public Optional<Fare> showOneFare(int idFare){
        return this.fareRepository.findById(idFare);
    }

    public void createPassenger(Passenger passenger){
        this.passengerRepository.save(passenger);
    }

    public int showAmountSeats(int idTrip){
        return flightResRepository.findPlaneSeats(idTrip);
    }

    public int getIdTripBookingDetail(int idTripBooking, int idCustomers, int idFares, String status){
        return flightResRepository.saveDetailTripbooking(idTripBooking, idCustomers, idFares, status);
    }

    public List<Document> showDocuments(){
        return this.documentRepository.findAll();
    }

    public Optional<Document> getOnedocument(int id){
        return this.documentRepository.findById(id);
    }

    public List<Integer> getReservedSeats(int idTrip){
        return this.flightResRepository.findReservedSeats(idTrip);
    }

    public List<Ticket> getTicketByReservation(int idReservation){
        return flightResRepository.findTicket(idReservation);
    }

    public List<ReservationByCustomer> reservationByCustomer(int idCustomer){
        return this.flightResRepository.reservationByCustomers(idCustomer);
    }

    public Optional<ReservationByCustomer> reservation(int idReservaion){
        return this.flightResRepository.reservation(idReservaion);
    }

    public void updateStatusReservation(int idReservation){
        this.flightResRepository.updateReservation(idReservation);
    }

    public void deleteReservation(int idReservation){
        this.flightResRepository.delete(idReservation);
    }

}
