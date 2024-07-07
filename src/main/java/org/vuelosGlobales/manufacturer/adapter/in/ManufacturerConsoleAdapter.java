package org.vuelosGlobales.manufacturer.adapter.in;

import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.manufacturer.application.ManufacturerService;
import org.vuelosGlobales.manufacturer.domain.Manufacturer;

import java.util.List;

public class ManufacturerConsoleAdapter {
    private final ManufacturerService manufacturerService;

    public ManufacturerConsoleAdapter(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    public void menuManufacturer(){

        Validaciones validacion = new Validaciones();

        menu:while (true) {

            System.out.println("1. Guardar fabricante");
            System.out.println("2. Actualizar fabricante");
            System.out.println("3. Eliminar fabricante");
            System.out.println("4. Listar fabricantes");
            System.out.println("5. Salir");
            int option = validacion.validarInt("Seleccione una opciòn del menú");
    
            switch (option) {
                case 1:
                    String name = validacion.validarString("Ingrese el nombre del fabricante");
                    Manufacturer manufacturer = new Manufacturer();
                    manufacturer.setName(name);
                    manufacturerService.saveManufacturer(manufacturer);
                    break;
                case 2:
                    List<Manufacturer> manufacturers = manufacturerService.findAllManufacturer();
                    manufacturers.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    Manufacturer manufacturerUpdate = validacion.validarExistId(
                            () -> manufacturerService.findByIdManufacturer(validacion.validarInt("Seleccione el id del fabricante que desea actualizar:\n"))
                    );
                    String newName = validacion.validarString("Ingrese el nuevo nombre del fabricante:\n");
                    manufacturerService.updateManufacturer(new Manufacturer(manufacturerUpdate.getId(), newName));
                    System.out.println("El fabricante ha sido actualizado\n");
                    break;
                case 3:
                    List<Manufacturer> manufacturersDelete = manufacturerService.findAllManufacturer();
                    manufacturersDelete.forEach(registro -> System.out.println(registro));
                    Manufacturer manufacturerDelete = validacion.validarExistId(
                            () -> manufacturerService.findByIdManufacturer(validacion.validarInt("Seleccione el id del fabricante que desea eliminar"))
                    );
                    manufacturerService.deleteManufacturer(manufacturerDelete.getId());
                    System.out.println("El fabricante" + manufacturerDelete.getName() + "ha sido eliminado");
                    break;

                case 4:
                    List<Manufacturer> manufacturersList = manufacturerService.findAllManufacturer();
                    System.out.println(String.format("| %-10s | %-20s |", "ID", "Name"));
                    manufacturersList.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    break;
                case 5:
                    break menu;

            }            

        }
    }

}
