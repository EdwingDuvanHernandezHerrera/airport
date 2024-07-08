package org.vuelosGlobales.tripcrew.adapter.in;

import org.vuelosGlobales.connection.domain.ConnInfoDTO;
import org.vuelosGlobales.connection.domain.Connections;
import org.vuelosGlobales.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.trip.domain.TripAirportDTO;
import org.vuelosGlobales.tripcrew.application.TripCrewService;
import org.vuelosGlobales.tripcrew.domain.TripCrew;
import org.vuelosGlobales.tripcrew.domain.TripCrewInfoDTO;
import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.airline.domain.Airline;

import java.util.List;

public class TripCrewConsoleAdap {
    private final TripCrewService tripCrewService;
    Validaciones validaciones = new Validaciones();
    public TripCrewConsoleAdap(TripCrewService tripCrewService) {
        this.tripCrewService = tripCrewService;
    }

    public void crew(){
        tripulation: while (true){
            System.out.println("1. Asignar tripulación a conexión");
            System.out.println("2. Ver tripulación de una conexión");
            System.out.println("3. regresar");
            int choice = validaciones.validarInt("Seleccione una opción del menú\n");
            switch (choice){
                case 1:
                    asignarTripulacion();
                    break;
                case 2:
                    verTripulacion();
                    break;
                case 3:
                    break tripulation;
            }
        }
    }

    public void asignarTripulacion(){

       getConn: while (true){
           showAirlines();
           Airline getAirline = validaciones.validarExistId(
                   () -> tripCrewService.getAirlineById(validaciones.validarInt("Seleccione la aerolínea a la que pertenece el vuelo: "))
           );
           int idAirline = getAirline.getId();
           showTripes();
           TripAirportDTO getTrip = validaciones.validarExistId(
                   () -> tripCrewService.getTripAirpById(validaciones.validarInt("Seleccione el viaje al que pertenece la conexión: "))
           );
           int idTrip = getTrip.getId();
           showConnections(idTrip);
           Connections getConn = validaciones.validarExistId(
                   () -> tripCrewService.getConnectionById(validaciones.validarInt("Seleccione la conexión por el Id Conn: "))
           );
           showEmployees(idAirline);
           EmployeeRelationshipDTO piloto = validaciones.validarExistId(
                   () -> tripCrewService.getEmployecById(validaciones.validarString("Seleccione al piloto por su ID de empleado:"))
           );
           tripCrewService.saveTripCrew(new TripCrew(piloto.getId(), getConn.getId()));
           EmployeeRelationshipDTO copiloto = validaciones.validarExistId(
                   () -> tripCrewService.getEmployecById(validaciones.validarString("Seleccione al copiloto por su ID de empleado:"))
           );
           tripCrewService.saveTripCrew(new TripCrew(copiloto.getId(), getConn.getId()));
           EmployeeRelationshipDTO azafata1 = validaciones.validarExistId(
                   () -> tripCrewService.getEmployecById(validaciones.validarString("Seleccione la azafata uno por su ID de empleado:"))
           );
           tripCrewService.saveTripCrew(new TripCrew(azafata1.getId(), getConn.getId()));
           EmployeeRelationshipDTO azafata2 = validaciones.validarExistId(
                   () -> tripCrewService.getEmployecById(validaciones.validarString("Seleccione la azafata dos por su ID de empleado:"))
           );
           tripCrewService.saveTripCrew(new TripCrew(azafata2.getId(), getConn.getId()));
           String pregunta = validaciones.validarString("Quiere asignar tripulación a otra conexión? (y/n): ");
           if (pregunta.equals("n")){
               break getConn;
           }
       }

    }

    public void verTripulacion(){
        showTripes();
        TripAirportDTO getTrip = validaciones.validarExistId(
                () -> tripCrewService.getTripAirpById(validaciones.validarInt("Seleccione el viaje al que pertenece la conexión: "))
        );
        showConnections(getTrip.getId());
        Connections getConn = validaciones.validarExistId(
                () -> tripCrewService.getConnectionById(validaciones.validarInt("Seleccione la conexión por el  Id Conn: "))
        );
        showTripCrew(getConn.getId());
    }

    public void showTripCrew(int idConn){
        List<TripCrewInfoDTO> crew = tripCrewService.getDataTripCrewByConn(idConn);
        if (crew.isEmpty()){
            System.out.println("Resultado vacío");
        }else{
            System.out.printf("\n| %-35s | %-20s | %-20s | %-40s |%n", "Nombre", "Rol", "Conexión", "Aeropuerto");
            crew.forEach(data -> {
                System.out.printf("\n| %-35s | %-20s | %-20s | %-40s |%n", data.getNameEmployee(), data.getRolEmployee(), data.getConnectionNumber(), data.getAirport());
            });
        }
    }

    public void showAirlines(){
        List<Airline> airlineList = tripCrewService.getAllAirlines();
        System.out.println("Listado de aerolíneas:");
        System.out.printf("\n| %-4s | %-16s |%n", "ID", "NOMBRE");
        airlineList.forEach(airline -> {
            System.out.printf("\n| %-4s | %-16s |%n", airline.getId(), airline.getName());
        });
        System.out.println();
    }

    public void showTripes(){
        List<TripAirportDTO> airports = tripCrewService.getAllTripAirp();
        System.out.println("Listado de viajes:");
        System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", "ID", "FECHA", "PRECIO", "ORIGEN", "DESTINATION");
        airports.forEach(airport -> {
            System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", airport.getId(), airport.getTripDate(), airport.getPriceTrip(), airport.getOrigin(), airport.getDestination());
        });
    }

    public void showConnections(int idTrip){
        List<ConnInfoDTO> connetions = tripCrewService.getAllConectionByTrip(idTrip);
        System.out.printf("\n| %-10s | %-12s | %-13s | %-40s |%n", "Id Conn", "Conn Number", "plates plane", "Aeropuerto");
        connetions.forEach(conn -> {
            System.out.printf("\n| %-10s | %-10s | %-10s | %-40s |%n", conn.getIdConn(), conn.getConnNumber(), conn.getPlates(), conn.getNameCityAirport());
        });
    }

    public void showEmployees(int idAriline){
        List<EmployeeRelationshipDTO> employeeList = tripCrewService.getAllEmployees(true, idAriline);
        if (employeeList.isEmpty()){
            System.out.println("Resultado vacío");
        }else{
            System.out.println("Listado de empleados:");
            System.out.printf("\n| %-11s | %-17s | %-15s | %-16s | %-28s | %-45s |%n", "ID", "NOMBRE", "FECHA", "ROL", "AEROLINEA", "AIRPORT");
            employeeList.forEach(employee -> {
                System.out.printf("\n| %-11s | %-17s | %-15s | %-16s | %-28s | %-45s |%n", employee.getId(), employee.getName(), employee.getIngressDate(), employee.getRolName(), employee.getAirlineName(), employee.getAirportName());
            });
        }
    }
}
