package org.vuelosGlobales.country.adapter.in;

import org.vuelosGlobales.country.application.CountryService;
import org.vuelosGlobales.country.domain.Country;
import org.vuelosGlobales.helpers.Validaciones;

import java.util.List;
import java.util.Optional;

public class CountryConsoleAdapter {
    private final CountryService countryService;
    Validaciones validaciones = new Validaciones();
    public CountryConsoleAdapter(CountryService countryService) {
        this.countryService = countryService;
    }

    public  void crudCountry(){
        menuCountry: while (true){
            System.out.println("======================================");
            System.out.println("             MENÚ DE PAÍSES           ");
            System.out.println("======================================");
            System.out.println("\t1. Crear País");
            System.out.println("\t2. Actualizar País");
            System.out.println("\t3. Buscar País por ID");
            System.out.println("\t4. Eliminar País");
            System.out.println("\t5. Listar todos los Países");
            System.out.println("\t6. Salir");
            System.out.println("======================================");
            int choice = validaciones.validarInt("Seleccione una opción del menú: ");

            switch (choice){
                case 1:
                    String name = validaciones.validarString("Nombre del pais: ");
                    String idCountry = validaciones.validarExistPlate("Ingrese el id del país",
                            id -> countryService.getCountryById(id)
                    );
                    countryService.createCountry(new Country(idCountry.toUpperCase(),name));
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    showCountries();
                    Country countrySelect = validaciones.validarExistId(
                            () -> countryService.getCountryById(validaciones.validarString("Seleccione el pais por el id: ").toUpperCase())
                    );
                    String newName = validaciones.validarString("Nuevo nombre del pais: ");
                    countrySelect.setName(newName);
                    countryService.updateCountry(countrySelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    showCountries();
                    Country countrySelectId = validaciones.validarExistId(
                            () -> countryService.getCountryById(validaciones.validarString("Seleccione el pais por el id: ").toUpperCase())
                    );
                    System.out.println(countrySelectId);
                    break;

                case 4:
                    showCountries();
                    Country countryDeleteId = validaciones.validarExistId(
                            () -> countryService.getCountryById(validaciones.validarString("Seleccione el pais por el id: ").toUpperCase())
                    );
                    String countryDelete = countryDeleteId.getId();
                    countryService.deleteCountry(countryDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;
                case 5:
                    showCountries();
                    System.out.println();
                    break;
                case 6:
                    break menuCountry;
            }
        }
    }

    public void showCountries(){
        List<Country> countryList = countryService.getAllCoutries();
        System.out.println("Listado de paises:");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "PAIS"));
        countryList.forEach(country -> {
            System.out.println(String.format("\n| %-4s | %-16s |", country.getId(), country.getName()));
        });
        System.out.println();
    }

}
