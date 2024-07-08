package org.vuelosGlobales.trip.adapter.in;

import org.vuelosGlobales.connection.domain.ConnInfoDTO;
import org.vuelosGlobales.connection.domain.Connections;
import org.vuelosGlobales.trip.application.TripService;
import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.trip.domain.Trip;
import org.vuelosGlobales.trip.domain.TripAirportDTO;
import org.vuelosGlobales.airport.domain.AirportCityDTO;
import org.vuelosGlobales.plane.domain.PlaneStMdDTO;

import java.sql.Date;
import java.util.List;

public class TripConsoleAdapter {
    private final TripService tripService;
    Validaciones validaciones = new Validaciones();

    public TripConsoleAdapter(TripService tripService) {
        this.tripService = tripService;
    }

    public void crudTrip() {
        menuTrip: while (true){
            System.out.println("1. Crear Viaje");
            System.out.println("2. Actualizar Viaje");
            System.out.println("3. Actualizar una conexión");
            System.out.println("4. Buscar Viaje por ID");
            System.out.println("5. Eliminar Viaje");
            System.out.println("6. Listar todos los Viajes");
            System.out.println("7. Salir");
            int choice = validaciones.validarInt("Seleccione una opción del menú:\n");

            switch (choice) {
                case 1 -> {
                    Date tripDate = validaciones.validarFecha("Fecha del viaje, formado (yyyy-mm-dd): ");
                    int priceTrip = validaciones.validarInt("Ingrese el precio del viaje: ");
                    showAirports();
                    AirportCityDTO objAirportOrigin = validaciones.validarExistId(
                            () -> tripService.getAirportCityById(validaciones.validarString("Selección el aeropuerto de origen por su ID: ").toUpperCase())
                    );
                    AirportCityDTO objAirportDestination = validaciones.validarExistId(
                            () -> tripService.getAirportCityById(validaciones.validarString("Selección el aeropuerto de destino por us ID: ").toUpperCase())
                    );
                    String idOrigen = objAirportOrigin.getId();
                    String idDestination = objAirportDestination.getId();
                    Trip st = new Trip();
                    st.setPriceTrip(priceTrip);
                    st.setTripDate(tripDate);
                    st.setIdOrigin(idOrigen);
                    st.setIdDestination(idDestination);
                    int tripId = tripService.createTrip(st);

                    String nroConexion1 = validaciones.validarString("Ingrese el número del vuelo entre " +objAirportOrigin.getNameCity() + " y " + objAirportDestination.getNameCity() + ": ");
                    showPlanes();
                    PlaneStMdDTO planeSelect1 = validaciones.validarExistId(
                            () -> tripService.getPlaneById(validaciones.validarInt("Selección el avión que despegara en " + objAirportOrigin.getNameCity() + ": "))
                    );
                    int idPlane1 = planeSelect1.getId();
                    Connections connection = new Connections();
                    connection.setConnectionNumber(nroConexion1);
                    connection.setIdTrip(tripId);
                    connection.setIdPlane(idPlane1);
                    connection.setIdAriport(idDestination);
                    tripService.createConnecion(connection);
                    String tipoVuelo = validaciones.validarString("¿El vuelo tiene escalas?(y/n): ");
                    if(tipoVuelo.equals("y")) {
                        masconexiones: while (true) {
                            String nroConexion = validaciones.validarString("Ingrese el número de la conexión");
                            showPlanes();
                            PlaneStMdDTO planeSelect = validaciones.validarExistId(
                                    () -> tripService.getPlaneById(validaciones.validarInt("Selección el avión: "))
                            );
                            int idPlane = planeSelect.getId();
                            showAirports();
                            AirportCityDTO getAirport = validaciones.validarExistId(
                                    () -> tripService.getAirportCityById(validaciones.validarString("Seleccione el aeropuerto donde se hace la conexión: "))
                            );
                            String idAirport = getAirport.getId();
                            Connections connections = new Connections();
                            connections.setConnectionNumber(nroConexion);
                            connections.setIdTrip(tripId);
                            connections.setIdPlane(idPlane);
                            connections.setIdAriport(idAirport);
                            tripService.createConnecion(connections);
                            String validar = validaciones.validarString("Quiere registrar otra conexión? (y/n): ");
                            if (validar.equals("n")) {
                                break masconexiones;
                            }
                        }
                    }
                }
                case 2 -> {
                    showTripes();
                    Trip airportSelect = validaciones.validarExistId(
                            () -> tripService.getTripById(validaciones.validarInt("Seleccione el viaje por su ID: "))
                    );
                    Date newDate = validaciones.validarFecha("Nueva fecha: ");
                    String newPreice = validaciones.validarString("Nuevo precio: ");
                    airportSelect.setTripDate(newDate);
                    airportSelect.setPriceTrip(Double.parseDouble(newPreice));
                    tripService.updateTrip(airportSelect);
                }
                case 3 -> {
                    showTripes();
                    TripAirportDTO getTrip = validaciones.validarExistId(
                            () -> tripService.getTripAripById(validaciones.validarInt("Elija el viaje cuya conexión desea actualizar: "))
                    );
                    System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", "ID", "FECHA", "PRECIO", "ORIGEN", "DESTINATION");
                    System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", getTrip.getId(), getTrip.getTripDate(), getTrip.getPriceTrip(), getTrip.getOrigin(), getTrip.getDestination());
                    showConnections(getTrip.getId());
                    Connections getConn = validaciones.validarExistId(
                            () -> tripService.getConnById(validaciones.validarInt("Seleccione la conexión a editar por su Id Conn: "))
                    );
                    String nroConxion = validaciones.validarString("Ingrese el nuevo número de la conexión:");
                    int idPlane;
                    String upPlane = validaciones.validarString("¿Desea cambiar el avión de esta conexión? (s/n): ");
                    if (upPlane.equals("s")) {
                        showPlanes();
                        PlaneStMdDTO getPlane = validaciones.validarExistId(
                                () -> tripService.getPlaneById(validaciones.validarInt("Seleccione el id del nuevo avión para esta conexión: "))
                        );
                        idPlane = getPlane.getId();
                    } else {
                        idPlane = getConn.getIdPlane();
                    }
                    String idAirport;
                    String upAirport = validaciones.validarString("¿Desea cambiar el aeropuerto de esta conexión? (s/n): ");
                    if (upAirport.equals("s")) {
                        showAirports();
                        AirportCityDTO getAirport = validaciones.validarExistId(
                                () -> tripService.getAirportCityById(validaciones.validarString("Seleccione el nuevo aeropuerto por su ID: "))
                        );
                        idAirport = getAirport.getId();
                    } else {
                        idAirport = getConn.getIdAriport();
                    }
                    System.out.println(idAirport);
                    System.out.println(idPlane);
                    getConn.setConnectionNumber(nroConxion);
                    getConn.setIdPlane(idPlane);
                    getConn.setIdAriport(idAirport);
                    tripService.updateConnection(getConn);
                }
                case 4 -> {
                    showTripes();
                    TripAirportDTO getTripObj = validaciones.validarExistId(
                            () -> tripService.getTripAripById(validaciones.validarInt("Selecciona el id del viaje: "))
                    );

                    System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", "ID", "FECHA", "PRECIO", "ORIGEN", "DESTINATION");
                    System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", getTripObj.getId(), getTripObj.getTripDate(), getTripObj.getPriceTrip(), getTripObj.getOrigin(), getTripObj.getDestination());
                    showConnections(getTripObj.getId());
                    break;
                }

                case 5 -> {
                    showTripes();
                    Trip showTripF = validaciones.validarExistId(
                            () -> tripService.getTripById(validaciones.validarInt("Seleccione el viaje por el id: "))
                    );
                    int tripDelete = showTripF.getId();
                    tripService.deleteTrip(tripDelete);
                    break;
                }
                case 6 -> {
                    showTripes();
                    break;
                }
                case 7 -> {
                    break menuTrip;
                }

            }
        }
    }

    public void showTripes(){
        List<TripAirportDTO> airports = tripService.getAllTripAirp();
        System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", "ID", "FECHA", "PRECIO", "ORIGEN", "DESTINATION");
        airports.forEach(airport -> {
            System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", airport.getId(), airport.getTripDate(), airport.getPriceTrip(), airport.getOrigin(), airport.getDestination());
        });
    }

    public void showAirports(){
        List<AirportCityDTO> listadoAeropuertos = tripService.getAllAirportCity();
        System.out.printf("\n| %-4s | %-30s | %-30s |%n", "ID", "Aeropuerto", "Ciudad");
        listadoAeropuertos.forEach(aeropuerto -> {
            System.out.printf("\n| %-4s | %-30s | %-30s |%n", aeropuerto.getId(), aeropuerto.getNameAirport(), aeropuerto.getNameCity());
        });
    }

    public void showPlanes(){
        List<PlaneStMdDTO> planeList = tripService.getAllPlanesInfo();
        System.out.printf("\n| %-4s | %-10s | %-10s | %-17s | %-25s | %-20s | %-20s |%n", "ID", "PLACA", "CAPACIDAD", "FECHAFAB", "AEROLÍNEA", "ESTADO", "MODELO");
        planeList.forEach(plane -> {
            System.out.printf("\n| %-4s | %-10s | %-10s | %-17s | %-25s | %-20s | %-20s |%n", plane.getId(), plane.getPlates(), plane.getCapacity(), plane.getFabricationDate(), plane.getNameAirline(), plane.getNameStatus(), plane.getNameModel());
        });
        System.out.println();
    }

    public void showConnections(int idTrip){
        List<ConnInfoDTO> connetions = tripService.getAllConnByTrip(idTrip);
        System.out.printf("\n| %-10s | %-10s | %-12s | %-13s | %-40s |%n", "Id Trip", "Id Conn", "Conn Number", "plates plane", "Aeropuerto");
        connetions.forEach(conn -> {
            System.out.printf("\n| %-10s | %-10s | %-10s | %-10s | %-40s |%n", conn.getIdTrip(), conn.getIdConn(), conn.getConnNumber(), conn.getPlates(), conn.getNameCityAirport());
        });
    }
}
