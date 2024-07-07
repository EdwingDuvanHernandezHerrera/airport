package org.vuelosGlobales.airport.adapter.in;

import org.vuelosGlobales.city.domain.City;
import org.vuelosGlobales.airport.domain.Airport;
import org.vuelosGlobales.airport.domain.AirportCityDTO;
import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.airport.application.AirportService;

import java.util.List;

public class AirportConsoleAdapter {
    private final AirportService airportService;
    Validaciones validaciones = new Validaciones();
    public AirportConsoleAdapter(AirportService airportService) {
        this.airportService = airportService;
    }

    public void crudAirport() {
        menuAirport: while (true){
            System.out.println("======================================");
            System.out.println("          MENÚ DE AEROPUERTOS         ");
            System.out.println("======================================");
            System.out.println("\t1. Crear Aeropuerto");
            System.out.println("\t2. Actualizar Aeropuerto");
            System.out.println("\t3. Buscar Aeropuerto por ID");
            System.out.println("\t4. Eliminar Aeropuerto");
            System.out.println("\t5. Listar todos los Aeropuertos");
            System.out.println("\t6. Salir");
            System.out.println("======================================");
            int choice = validaciones.validarInt("Seleccione una opción del menú: ");

            switch (choice){
                case 1:
                    showCityes();
                    City objCity = validaciones.validarExistId(
                            () -> airportService.getCityById(validaciones.validarString("Selección el id de la ciudad donde está ubicado: ").toUpperCase())
                    );
                    String name = validaciones.validarString("Nombre del aeropuerto: ");
                    String getAirport = validaciones.validarExistPlate(
                            "Ingrese el id del aeropuerto, debe ser alfanumerico de máximo 5 caracteres: ",
                            id -> airportService.getAirportById(id)
                    );
                    Airport st = new Airport();
                    st.setId(getAirport);
                    st.setName(name);
                    st.setIdCity(objCity.getId());
                    airportService.createAirport(st);
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                  showAirportes();
                    Airport airportSelect = validaciones.validarExistId(
                            () -> airportService.getAirportById(validaciones.validarString("Seleccione el aeropuerto por el id: ").toUpperCase())
                    );
                    String newName = validaciones.validarString("Nuevo nombre del aeropuerto: ");
                    String idCity;
                    String updateCity = validaciones.yesOrNo("Quiere cambiar la ubicación de este aeropuerto? (y/n): ");
                    if (updateCity.equals("y")){
                        showCityes();
                        City selectCity = validaciones.validarExistId(
                                () -> airportService.getCityById(validaciones.validarString("Seleccione el id de la nueva ciudad: ").toUpperCase())
                        );
                        idCity = selectCity.getId();
                    }else {
                        idCity = airportSelect.getIdCity();
                    }
                    airportSelect.setName(newName);
                    airportSelect.setIdCity(idCity);
                    airportService.updateAirport(airportSelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    showAirportes();
                    AirportCityDTO showAirport = validaciones.validarExistId(
                            () -> airportService.getAirportCityById(validaciones.validarString("Seleccione el aeropuerto por el id: ").toUpperCase())
                    );
                    System.out.println(showAirport);
                    break;

                case 4:
                    showAirportes();
                    Airport showAirportF = validaciones.validarExistId(
                            () -> airportService.getAirportById(validaciones.validarString("Seleccione el aeropuerto por el id: ").toUpperCase())
                    );
                    String airportDelete = showAirportF.getId();
                    airportService.deleteAirport(airportDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;

                case 5:
                    showAirportes();
                    break;

                case 6:
                    break menuAirport;
            }
        }
    }

    public void showAirportes(){
        List<AirportCityDTO> airports = airportService.getAllAirportCity();
        System.out.println(String.format("\n| %-5s | %-22s | %-20s |", "ID", "NOMBRE", "CIUDAD"));
        airports.forEach(airport -> {
            System.out.println(String.format("\n| %-5s | %-22s | %-20s |", airport.getId(), airport.getNameAirport(), airport.getNameCity()));
        });
        System.out.println();
    }

    public void showCityes(){
        List<City> cities = airportService.getAllCities();
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        cities.forEach(city -> {
            System.out.println(String.format("\n| %-4s | %-16s |", city.getId(), city.getName()));
        });
        System.out.println();
    }
}
