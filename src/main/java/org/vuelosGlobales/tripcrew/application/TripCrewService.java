package org.vuelosGlobales.tripcrew.application;

import org.vuelosGlobales.connection.domain.ConnInfoDTO;
import org.vuelosGlobales.connection.domain.Connections;
import org.vuelosGlobales.connection.infrastructure.ConnectionRepository;
import org.vuelosGlobales.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.employee.infrastructure.EmployeeRepository;
import org.vuelosGlobales.trip.domain.TripAirportDTO;
import org.vuelosGlobales.trip.infrastructure.TripRepository;
import org.vuelosGlobales.tripcrew.domain.TripCrew;
import org.vuelosGlobales.tripcrew.domain.TripCrewInfoDTO;
import org.vuelosGlobales.tripcrew.infrastructure.TripCrewRepository;
import org.vuelosGlobales.airline.domain.Airline;
import org.vuelosGlobales.airline.infrastructure.AirlineRepository;

import java.util.List;
import java.util.Optional;

public class TripCrewService {
    private final TripCrewRepository tripCrewRepository;
    private final AirlineRepository airlineRepository;
    private final EmployeeRepository employeeRepository;
    private final ConnectionRepository connectionRepository;
    private final TripRepository tripRepository;

    public TripCrewService(TripCrewRepository tripCrewRepository, AirlineRepository airlineRepository, EmployeeRepository employeeRepository, ConnectionRepository connectionRepository, TripRepository tripRepository) {
        this.tripCrewRepository = tripCrewRepository;
        this.airlineRepository = airlineRepository;
        this.employeeRepository = employeeRepository;
        this.connectionRepository = connectionRepository;
        this.tripRepository = tripRepository;
    }

    public void saveTripCrew(TripCrew tripCrew){
        tripCrewRepository.save(tripCrew);
    }

    public List<TripCrewInfoDTO> getDataTripCrewByConn(int idConn){
        return tripCrewRepository.showCrewByConn(idConn);
    }

    public List<Airline> getAllAirlines(){
        return airlineRepository.findAll();
    }

    public Optional<Airline> getAirlineById(int id){
        return airlineRepository.findById(id);
    }

    public List<EmployeeRelationshipDTO> getAllEmployees(boolean filter, int id){
        return employeeRepository.findAllEmployeesInfo(filter, id);
    }

    public Optional<EmployeeRelationshipDTO> getEmployecById(String id){
        return employeeRepository.findEmployeeInfoById(id);
    }

    public List<TripAirportDTO> getAllTripAirp(){
        return tripRepository.findAllTripAirport();
    }
    public Optional<TripAirportDTO> getTripAirpById(int id){
        return tripRepository.findTripAirportById(id);
    }

    public List<ConnInfoDTO> getAllConectionByTrip(int idTrip){
        return connectionRepository.findAllConnByTrip(idTrip);
    }

    public Optional<Connections> getConnectionById(int id){
        return connectionRepository.findById(id);
    }
}
