package org.vuelosGlobales.airline.adapter.in;

import org.vuelosGlobales.airline.domain.Airline;
import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.airline.application.AirlineService;
import org.vuelosGlobales.status.domain.Status;

import java.util.List;

public class AirlineConsoleAdap {
    private final AirlineService airlineService;
    Validaciones validaciones = new Validaciones();

    public AirlineConsoleAdap(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    public  void crudAirline(){
        menuAirline: while (true){
            System.out.println("1. Crear Aerolínea");
            System.out.println("2. Actualizar Aerolínea");
            System.out.println("3. Buscar Aerolínea por ID");
            System.out.println("4. Eliminar Aerolínea");
            System.out.println("5. Listar todos Aerolíneas");
            System.out.println("6. Salir");
            int choice = validaciones.validarInt("Seleccione una opción del menú");

            switch (choice){
                case 1:
                    String name = validaciones.validarString("Nombre de la aerolínea: ");
                    Airline airline = new Airline();
                    airline.setName(name);
                    airlineService.createAirline(airline);
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    List<Airline> airlines = airlineService.getAllAirlines();
                    airlines.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    Airline airlineSelect = validaciones.validarExistId(
                            () -> airlineService.getAirlineById(validaciones.validarInt("Seleccione el id de la aerolínea: "))
                    );
                    String newName = validaciones.validarString("Nuevo nombre de la aerolínea: ");
                    airlineSelect.setName(newName);
                    airlineService.updateAirline(airlineSelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    List<Airline> airlines3 = airlineService.getAllAirlines();
                    airlines3.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    Airline showAirline = validaciones.validarExistId(
                            () -> airlineService.getAirlineById(validaciones.validarInt("Seleccione el id de la aerolínea: "))
                    );
                    System.out.println(showAirline);
                    System.out.println();
                    break;

                case 4:
                    List<Airline> airlines4 = airlineService.getAllAirlines();
                    airlines4.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    Airline findAirlineD = validaciones.validarExistId(
                            () -> airlineService.getAirlineById(validaciones.validarInt("Seleccione el id de la aerolínea: "))
                    );
                    int airlineDelete = findAirlineD.getId();
                    airlineService.deleteAirline(airlineDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;
                case 5:
                    List<Airline> airlines5 = airlineService.getAllAirlines();
                    airlines5.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    break;
                case 6:
                    break menuAirline;
            }
        }
    }
}
