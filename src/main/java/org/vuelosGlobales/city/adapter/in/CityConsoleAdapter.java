package org.vuelosGlobales.city.adapter.in;

import org.vuelosGlobales.city.application.CityService;
import org.vuelosGlobales.city.domain.City;
import org.vuelosGlobales.city.domain.CityCountryDTO;
import org.vuelosGlobales.country.domain.Country;
import org.vuelosGlobales.helpers.Validaciones;

import java.util.List;
import java.util.Optional;

public class CityConsoleAdapter {
    private final CityService cityService;
    Validaciones validaciones = new Validaciones();

    public CityConsoleAdapter(CityService cityService) {
        this.cityService = cityService;
    }

    public  void crudCity(){
        menuCity: while (true){
            System.out.println("======================================");
            System.out.println("            MENÚ DE CIUDADES          ");
            System.out.println("======================================");
            System.out.println("\t1. Crear Ciudad");
            System.out.println("\t2. Actualizar Ciudad");
            System.out.println("\t3. Buscar Ciudad por ID");
            System.out.println("\t4. Eliminar Ciudad");
            System.out.println("\t5. Listar todas las Ciudades");
            System.out.println("\t6. Salir");
            System.out.println("======================================");
            int choice = validaciones.validarInt("Seleccione una opción del menú: ");

            switch (choice){
                case 1:
                    showCountries();
                    Country countrySelect = validaciones.validarExistId(
                            () -> cityService.getCountryById(validaciones.validarString("Seleccione el pais por el id: ").toUpperCase())
                    );

                    String idCountry = countrySelect.getId();
                    String name = validaciones.validarString("Ingrese el nombre de la ciudad: \n");
                    String idCity = validaciones.validarExistPlate(
                            "Ingrese el ID de la ciudad (alfanumérico, máximo 5 caracteres): ",
                            id -> cityService.getCityById(id)
                    );
                    cityService.createCity(new City(idCity.toUpperCase(), name, idCountry.toUpperCase()));
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    showCities();
                    City citySelect = validaciones.validarExistId(
                            () -> cityService.getCityById(validaciones.validarString("Seleccione la ciudad por el id: ").toUpperCase())
                    );

                    String newName = validaciones.validarString("Nuevo nombre del ciudad: ");
                    String idCountryCity;
                    String validate = validaciones.yesOrNo("Quiere cambiar el país al que pertenece la ciudad? (y/n)");
                    if (validate.equals("y")){
                        showCountries();
                        Country getObjP = validaciones.validarExistId(
                                () -> cityService.getCountryById(validaciones.validarString("A que país pertenece la ciudad, seleccione por el por el id: ").toUpperCase())
                        );
                        idCountryCity = getObjP.getId();
                    }else {
                        idCountryCity = citySelect.getIdCountry();
                    }
                    citySelect.setName(newName);
                    citySelect.setIdCountry(idCountryCity);
                    cityService.updateCity(citySelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    showCities();
                    City showCity = validaciones.validarExistId(
                        () -> cityService.getCityById(validaciones.validarString("Seleccione la ciudad por el id: ").toUpperCase())
                    );
                    System.out.println(showCity);
                    break;

                case 4:
                    showCities();
                    City showCityD = validaciones.validarExistId(
                            () -> cityService.getCityById(validaciones.validarString("Seleccione la ciudad por el id: ").toUpperCase())
                    );
                    String cityDelete = showCityD.getId();
                    cityService.deleteCity(cityDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;
                case 5:
                    showCities();
                    break;
                case 6:
                    break menuCity;
            }
        }
    }

    public void showCities(){
        List<CityCountryDTO> cityList = cityService.getCityWithCountry();
        System.out.println(String.format("\n| %-6s | %-17s | %-17s |", "ID", "CIUDAD", "PAIS"));
        cityList.forEach(city -> {
            System.out.println(String.format("\n| %-6s | %-17s | %-17s |", city.getId(), city.getNameCity(), city.getNameCountry()));
        });
        System.out.println();
    }

    public void showCountries(){      List<Country> countryList = cityService.getAllCoutries();
        System.out.println("Listado de paises:");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "PAIS"));
        countryList.forEach(country -> {
            System.out.println(String.format("\n| %-4s | %-16s |", country.getId(), country.getName()));
        });
        System.out.println();
    }

}
