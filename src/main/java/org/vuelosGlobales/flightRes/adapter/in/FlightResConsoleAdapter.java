package org.vuelosGlobales.flightRes.adapter.in;

import org.vuelosGlobales.passenger.domain.Passenger;
import org.vuelosGlobales.trip.domain.TripAirportDTO;
import org.vuelosGlobales.customer.domain.Customer;
import org.vuelosGlobales.flightRes.application.FlightResService;
import org.vuelosGlobales.flightRes.domain.FlightRes;
import org.vuelosGlobales.flightRes.domain.ReservationByCustomer;
import org.vuelosGlobales.flightRes.domain.Ticket;
import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.document.domain.Document;
import org.vuelosGlobales.fare.domain.Fare;

import java.util.ArrayList;
import java.util.List;

public class FlightResConsoleAdapter {
    private final FlightResService flightResService;
    Validaciones validaciones = new Validaciones();

    public FlightResConsoleAdapter(FlightResService flightResService) {
        this.flightResService = flightResService;
    }


    public void crudFlightRes(){
        makeReservation: while (true){
            System.out.println("+--------------------------------+");
            System.out.println("|           MENU PRINCIPAL       |");
            System.out.println("+--------------------------------+");
            System.out.printf("| %-2d. %-26s |\n", 1, "Ver los vuelos disponibles");
            System.out.printf("| %-2d. %-26s |\n", 2, "Hacer reservación");
            System.out.printf("| %-2d. %-26s |\n", 3, "Ver reservas por clientes");
            System.out.printf("| %-2d. %-26s |\n", 4, "Cancelar reservación");
            System.out.printf("| %-2d. %-26s |\n", 5, "Eliminar reservación");
            System.out.printf("| %-2d. %-26s |\n", 6, "Volver al menú anterior");
            System.out.println("+--------------------------------+");
            System.out.println("====================================");
            int choise = validaciones.validarInt("Seleccione una opción del menú: ");

            switch (choise){
                case 1:
                    mostrarViajes();
                    break;
                case 2:
                    mostrarClientes();
                    Customer cliente = seleccionarCliente();
                    mostrarViajes();
                    TripAirportDTO vuelo = seleccionarVuelo();
                    int asientosAvion = flightResService.showAmountSeats(vuelo.getId());
                    int idReserva = generarReserva(vuelo.getId());
                    //Obtener todos los datos de la reserva que vamos de crear
                    FlightRes reservation = validaciones.validarExistId(
                            () -> flightResService.showOneFlightBooking(idReserva)
                    );

                    List<Fare> fares = flightResService.showAllFares();
//                  Crear reservación para el cliente seleccionado
                    List<Integer> asientosReservadosC = flightResService.getReservedSeats(vuelo.getId());
                    Passenger datosCliente = new Passenger();
                    datosCliente.setName(cliente.getName());
                    datosCliente.setLastName(cliente.getLastName());
                    datosCliente.setNroId(cliente.getNroId());
                    int seatC = seleccionarAsiento(asientosAvion, asientosReservadosC);
                    datosCliente.setSeat(seatC);
                    datosCliente.setIdDocument(cliente.getIdDocument());
                    int idDetalleReserva = generarDetalleDeReserva(fares, reservation, cliente, vuelo);
                    datosCliente.setIdTripBookingDetails(idDetalleReserva);
                    flightResService.createPassenger(datosCliente);

                    String masPasajero = validaciones.yesOrNo("Viajas con alguien más? (y/n): ");
                    if (masPasajero.equals("y")){
                        int cantidadPasajeros = validaciones.validarInt("Cuantas personas van a viajar con usted? ");
                        for (int i = 1; i <= cantidadPasajeros; i++){
                            Passenger passenger = new Passenger();
                            List<Integer> asientosReservadosP = flightResService.getReservedSeats(vuelo.getId());
                            String name = validaciones.validarString("Nombre del pasajero " + i + ": ");
                            String lastName = validaciones.validarString("Apellidos del pasajero " + i + ": ");
                            int age = validaciones.validarInt("Edad del pasajero " + i + ": ");
                            int idDocument = obtenerTipoDocumento();
                            int nroId = validaciones.validarInt("Ingrese el número de documento: ");
                            int seatP = seleccionarAsiento(asientosAvion, asientosReservadosP);
                            int idDetalleReservaP = generarDetalleDeReserva(fares, reservation, cliente, vuelo);
                            passenger.setIdTripBookingDetails(idDetalleReservaP);
                            passenger.setSeat(seatP);
                            passenger.setName(name);
                            passenger.setLastName(lastName);
                            passenger.setAge(age);
                            passenger.setIdDocument(idDocument);
                            passenger.setNroId(nroId);
                            flightResService.createPassenger(passenger);
                            System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", "ID", "FECHA", "PRECIO", "ORIGEN", "DESTINATION");
                            System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", vuelo.getId(), vuelo.getTripDate(), vuelo.getPriceTrip(), vuelo.getOrigin(), vuelo.getDestination());
                        }
                    }
                    mostrarTicket(idReserva, vuelo.getPriceTrip());
                    break;
                case 3:
                    mostrarClientes();
                    Customer getCustomer = seleccionarCliente();

                    reservaPorCliente(getCustomer.getId());

                    break;
                case 4:
                    mostrarClientes();
                    Customer getCliente = seleccionarCliente();
                    reservaPorCliente(getCliente.getId());
                    int idCacelar = validaciones.validarInt("Seleccione el ID de la reservacion que va a cancelar: ");
                    String cancelar = validaciones.yesOrNo("Esta seguro que desea cancelar esta reserva? (y/n): ");
                    if (cancelar.equals("y")){
                        flightResService.updateStatusReservation(idCacelar);
                    }else {
                    }
                    System.out.println();
                    break;
                case 5:
                    mostrarClientes();
                    Customer clienteDelete = seleccionarCliente();
                    reservaPorCliente(clienteDelete.getId());
                    int idDelete = validaciones.validarInt("Seleccione el ID de la reservacion que va a eliminar: ");
                    String eliminar = validaciones.yesOrNo("Esta seguro que desea eliminar esta reserva? (y/n): ");
                    if (eliminar.equals("y")){
                        flightResService.deleteReservation(idDelete);
                    }else {
                    }
                    break;

                case 6:
                    break makeReservation;
            }
        }
    }

    public void mostrarViajes(){
        List<TripAirportDTO> airports = flightResService.showAllTrips();
        System.out.println("Listado de viajes:");
        System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", "ID", "FECHA", "PRECIO", "ORIGEN", "DESTINATION");
        airports.forEach(airport -> {
            System.out.printf("\n| %-5s | %-11s | %-10s | %-40s | %-40s |%n", airport.getId(), airport.getTripDate(), airport.getPriceTrip(), airport.getOrigin(), airport.getDestination());
        });
    }

    public void mostrarClientes(){
        List<Customer> customerList = flightResService.showAllCustomers();
        System.out.printf("\n| %-6s | %-20s | %-20s | %-20s | %-6s |%n", "ID", "NOMBRE", "APELLIDOS", "#ID", "EDAD");
        customerList.forEach(customer -> {
            System.out.printf("\n| %-6s | %-20s | %-20s | %-20s | %-6s |%n", customer.getId(), customer.getName(), customer.getLastName(), customer.getNroId(), customer.getAge());
        });
    }

    public Customer seleccionarCliente(){
        return validaciones.validarExistId(
                () -> flightResService.showOneCustomer(validaciones.validarInt("Seleccione el cliente por su ID: "))
        );
    }

    public TripAirportDTO seleccionarVuelo(){
        return validaciones.validarExistId(
                () -> flightResService.showOneTrip(validaciones.validarInt("Seleccione el ID del vuelo que va a reservar: "))
        );
    }

    public int generarReserva(int idViaje){
        FlightRes flightRes = new FlightRes();
        flightRes.setIdTrip(idViaje);
        int idReservacion = flightResService.createFlightBooking(flightRes);
        return idReservacion;
    }

    public void mostrarTarifas(List<Fare> fares){
        System.out.printf("\n| %-4s | %-16s | %-25s | %-25s |%n", "ID", "Descripción", "Detalle", "Valor");
        fares.forEach(fare -> {
            System.out.printf("\n| %-4s | %-16s | %-25s | %-25s |%n", fare.getId(), fare.getDescription(), fare.getDetails(), fare.getValue());
        });
    }

    public Fare seleccionarTarifa(){
        return validaciones.validarExistId(
                () -> flightResService.showOneFare(validaciones.validarInt("Seleccione el ID de la tarifa: "))
        );
    }

    public int generarDetalleDeReserva(List<Fare> fares, FlightRes reservation, Customer cliente, TripAirportDTO vuelo){
        String equipaje = validaciones.yesOrNo("Llevas equipaje? (y/n): ");
        int idTarifa;
        if (equipaje.equals("y")){
            mostrarTarifas(fares);
            Fare fare = seleccionarTarifa();
            idTarifa = fare.getId();
        }else{
            idTarifa = 2;
        }
        // Almacenar datos en tripBookingDetails, para poder crear en passenger, ya que el passanger tiene la llave primaria de tripBookingDetails
        int idTripBooking = reservation.getId();
        int idCustomer = cliente.getId();
        int idFare = idTarifa;
        String status = "active";

        return flightResService.getIdTripBookingDetail(idTripBooking, idCustomer, idFare, status);
    }

    public int obtenerTipoDocumento(){
        List<Document> documents = flightResService.showDocuments();
        System.out.println("Documentos registrados:");
        System.out.printf("\n| %-4s | %-20s |%n", "ID", "NOMBRE");
        documents.forEach(document -> {
            System.out.printf("\n| %-4s | %-20s |%n", document.getId(), document.getName());
        });
        System.out.println();

        Document idDocument = validaciones.validarExistId(
                () -> flightResService.getOnedocument(validaciones.validarInt("Seleccione el tipo de documento por el ID: "))
        );
        return idDocument.getId();
    }

    public List<Integer> ObteneAsientosReservados(int idTrip){
        return flightResService.getReservedSeats(idTrip);
    }

    public int seleccionarAsiento(int totalAsientos, List<Integer> asientosReservados){
        List<Integer> capacityPlane = new ArrayList<>();
        for (int i = 1; i <= totalAsientos; i++){
            capacityPlane.add(i);
        }

        int count = 0;
        int pasillo = 0;
        for (int i = 1; i <= totalAsientos; i++){
          if (asientosReservados.contains(i)) {
                System.out.print(" X ");
            } else {
                System.out.print(String.format("%3d", i));
            }
            count++;
            pasillo++;

            if (pasillo == 3) {
                System.out.print(" ___ "); // Simular el pasillo
            }
            if (pasillo == 6){
                pasillo = 0;
            }
            if (count % 6 == 0) {
                System.out.println();
            }
        }

        if (count % 6 != 0) {
            System.out.println();
        }


        int seatSelect;
        while (true){
            int seat = validaciones.validarInt("seleccione un asiento: ");
            if (seat > totalAsientos){
                System.out.println("El asiento ingresado no existe, ");
            }else {
                if (asientosReservados.contains(seat)){
                    System.out.println("El asiento seleccionado ya esta reservado, ");
                }else {
                    seatSelect = seat;
                    break;
                }
            }
        }
        return seatSelect;
    }


    public void mostrarTicket(int idReservacion, double precio){
        System.out.println("\n---- Pasajeros ----");
        List<Ticket> tickets = flightResService.getTicketByReservation(idReservacion);
        Double priceFare = 0D;
        System.out.println(String.format("\n| %-4s | %-20s | %-20s | %-17s | %-10s | %-25s | %-15s |", "ID", "Nombre", "Apellidos", "Documento", "Puesto", "Tarifa", "Valor tarifa"));
        for(Ticket ticket : tickets) {
            priceFare += ticket.getValue();
            System.out.println(String.format("\n| %-4s | %-20s | %-20s | %-17s | %-10s | %-25s | %-15s |", ticket.getIdReserva(), ticket.getName(), ticket.getLastName(), ticket.getNroId(), ticket.getSeat(), ticket.getDescriptionFare(), ticket.getValue()));
        }
        System.out.println();
        double costoVuelo = precio * tickets.size();
        double total = costoVuelo + priceFare;
        System.out.println("Total a pagar: " + total);


    }

    public void reservaPorCliente(int idCliente){
        List<ReservationByCustomer> reservas = flightResService.reservationByCustomer(idCliente);
        System.out.printf("\n| %-15s | %-11s | %-10s | %-40s | %-40s |%n", "Id reservasion", "Fecha", "Precio", "Origen", "Destino");
        reservas.forEach(vuelo -> {
            System.out.printf("\n| %-15s | %-11s | %-10s | %-40s | %-40s |%n", vuelo.getIdReservacion(), vuelo.getDate(), vuelo.getPrice(), vuelo.getOrigin(), vuelo.getDestination());
        });
        ReservationByCustomer reserva = validaciones.validarExistId(
                ()-> flightResService.reservation(validaciones.validarInt("\nSeleccione la reservacion por el ID: "))
        );

        mostrarTicket(reserva.getIdReservacion(), reserva.getPrice());
    }
}
