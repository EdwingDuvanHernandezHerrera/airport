package org.vuelosglobales.manufacturer.adapter.in;

import org.vuelosglobales.manufacturer.application.ManufacturerService;
import org.vuelosglobales.manufacturer.domain.Manufacturer;

import java.util.List;
import java.util.Scanner;

public class ManufacturerConsoleAdapter {
    private final ManufacturerService manufacturerService;

    public ManufacturerConsoleAdapter(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    public void menuManufacturer(){
        
        Scanner scanner = new Scanner(System.in);

        menu:while (true) {

            System.out.println("1. Guardar fabricante");
            System.out.println("2. Actualizar fabricante");
            System.out.println("3. Eliminar fabricante");
            System.out.println("4. Listar fabricantes");
            System.out.println("5. Salir");
            System.out.println("Seleccione una opciòn del menú");
            int option = Integer.parseInt(scanner.nextLine());
    
            switch (option) {
                case 1:
                    System.out.println("Ingrese el nombre del fabricante");
                    String name = scanner.nextLine();
                    Manufacturer manufacturer = new Manufacturer();
                    manufacturer.setName(name);
                    manufacturerService.saveManufacturer(manufacturer);
                    break;
                case 2:
                    List<Manufacturer> manufacturers = manufacturerService.findAllManufacturer();
                    manufacturers.forEach(registro -> System.out.println(registro));
                    System.out.println("Seleccione el id del fabricante que desea actualizar");
                    int id = scanner.nextInt();
                    System.out.println("Ingrese el nuevo nombre del fabricante");
                    String newName = scanner.nextLine();
                    manufacturerService.updateManufacturer(new Manufacturer(id, newName));
                    
                    break;
                
                    
                case 3:
                
                    break;
                case 4:
                
                    break;
                
                case 5:
    
                    break menu;    
            
                default:
                    break;
            }            

        }






        scanner.close();

    }


}
