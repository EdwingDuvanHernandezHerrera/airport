package org.vuelosGlobales.trip.application;

import org.vuelosGlobales.connection.domain.ConnInfoDTO;
import org.vuelosGlobales.connection.domain.Connections;
import org.vuelosGlobales.connection.infrastructure.ConnectionRepository;
import org.vuelosGlobales.trip.domain.Trip;
import org.vuelosGlobales.trip.domain.TripAirportDTO;
import org.vuelosGlobales.trip.infrastructure.TripRepository;
import org.vuelosGlobales.airport.domain.AirportCityDTO;
import org.vuelosGlobales.airport.infrastructure.AirportRepository;
import org.vuelosGlobales.plane.domain.PlaneStMdDTO;
import org.vuelosGlobales.plane.infrastructure.PlaneRepository;

import java.util.List;
import java.util.Optional;

public class TripService {
    private final TripRepository tripRepository;
    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;
    private final ConnectionRepository connectionRepository;

    public TripService(TripRepository tripRepository, AirportRepository airportRepository, PlaneRepository planeRepository, ConnectionRepository connectionRepository) {
        this.tripRepository = tripRepository;
        this.airportRepository = airportRepository;
        this.planeRepository = planeRepository;
        this.connectionRepository = connectionRepository;
    }

    public int createTrip(Trip trip){
       return this.tripRepository.save(trip);
    }

    public void updateTrip(Trip trip){
        this.tripRepository.update(trip);
    }

    public Optional<Trip> getTripById(int id){
        return this.tripRepository.findById(id);
    }

    public List<Trip> getAllTrips(){
        return this.tripRepository.findAll();
    }

    public void deleteTrip(int id){
        this.tripRepository.delete(id);
    }

    public List<AirportCityDTO> getAllAirportCity(){
        return this.airportRepository.findAllAirportCity();
    }

    public Optional<AirportCityDTO> getAirportCityById(String id){
        return this.airportRepository.findAirportCityById(id);
    }

    public List<TripAirportDTO> getAllTripAirp(){
        return this.tripRepository.findAllTripAirport();
    }

    public Optional<TripAirportDTO> getTripAripById(int id){
        return this.tripRepository.findTripAirportById(id);
    }

    public List<PlaneStMdDTO> getAllPlanesInfo(){
        return this.planeRepository.findAllPlaneStMd(false, 0);
    }

    public Optional<PlaneStMdDTO>getPlaneById(int id){
        return this.planeRepository.findPlaneStMdById(id);
    }

    public void createConnecion(Connections connections){
        this.connectionRepository.save(connections);
    }

    public List<ConnInfoDTO> getAllConnByTrip(int idTrip){
        return this.connectionRepository.findAllConnByTrip(idTrip);
    }

    public Optional<Connections> getConnById(int idConn){
        return this.connectionRepository.findById(idConn);
    }

    public void updateConnection(Connections conn){
        this.connectionRepository.update(conn);
    }

}
