package org.vuelosglobales.helpers;

import org.vuelosglobales.manufacturer.adapter.in.ManufacturerConsoleAdapter;
import org.vuelosglobales.manufacturer.adapter.out.ManufacturerMYSQLRepository;
import org.vuelosglobales.manufacturer.application.ManufacturerService;
import org.vuelosglobales.model.adapter.in.ModelConsoleAdapter;
import org.vuelosglobales.model.adapter.out.ModelMYSQLRepository;
import org.vuelosglobales.model.application.ModelService;

public class Menus {

    final String url = "jdbc:mysql://localhost:3306/airport";
    final String user = "campus2024";
    final String password = "Edhh0205761";

    

    ManufacturerMYSQLRepository manufacturerOut = new ManufacturerMYSQLRepository(url, user, password);
    ManufacturerService manufacturerService = new ManufacturerService(manufacturerOut);
    ManufacturerConsoleAdapter manufacturerIn = new ManufacturerConsoleAdapter(manufacturerService);
    
    ModelMYSQLRepository modelOut = new ModelMYSQLRepository(url, user, password);
    ModelService modelService = new ModelService(modelOut, manufacturerOut);
    ModelConsoleAdapter modelIn = new ModelConsoleAdapter(modelService);
    

    Validaciones validaciones = new Validaciones();
    
    public void menuPrincipal(){
        menuAdmin: while(true){
            System.out.println("======================================");
            System.out.println("           MENÚ DE ROLES               ");
            System.out.println("======================================");
            System.out.println("\t1. Administrador del sistema");
            System.out.println("\t2. Agente de ventas");
            System.out.println("\t3. Técnico en mantenimiento");
            System.out.println("\t5. Salir");
            System.out.println("======================================");
            int opcion = validaciones.validarInt("Seleccione un opción: ");

            if (opcion == 1){
                menuSystemAdmin();
            }else if (opcion == 2){
                optionsSalesAgent();
            } else if (opcion == 3) {
//                revisionIn.crudRevision();
            } else if (opcion == 5) {
                break menuAdmin;
            }
        }
    }

    public void menuSystemAdmin(){
        menuAdmin: while(true){
            System.out.println("======================================");
            System.out.println("            MENÚ PRINCIPAL           ");
            System.out.println("======================================");
            System.out.println("\t1.  Gestionar los aviones");
            System.out.println("\t2.  Gestionar los aeropuertos");
            System.out.println("\t3.  Gestionar aerolíneas");
            System.out.println("\t4.  Gestionar los trayectos y sus escalas");
            System.out.println("\t5.  Gestionar empleados");
            System.out.println("\t6.  Gestionar tripulación");
            System.out.println("\t7.  Gestionar información de roles de la tripulación");
            System.out.println("\t8.  Gestionar las tarifas de vuelo");
            System.out.println("\t9.  Gestionar documentos");
            System.out.println("\t10. Gestionar información de fabricantes de aviones");
            System.out.println("\t11. Gestionar información de modelos de avión");
            System.out.println("\t12. Gestionar información de estados de un avión");
            System.out.println("\t13. Gestionar información de países");
            System.out.println("\t14. Gestionar información de ciudades");
            System.out.println("\t15. Regresar al menú principal");
            System.out.println("======================================");
            System.out.print("Seleccione una opción: ");

            int opcion = validaciones.validarInt("Seleccione un opción: ");

            if (opcion == 1){
//                planeIn.crudPlane();
            } else if (opcion == 2){
//                airportIn.crudAirport();
            } else if (opcion == 3){
//                airlineIn.crudAirline();
            } else if (opcion == 4) {
//                tripConsoleAdapter.crudTrip();
            } else if (opcion == 13) {
//                countryIn.crudCountry();
            } else if (opcion == 14) {
//                cityIn.crudCity();
            } else if (opcion == 10) {
                manufacturerIn.menuManufacturer();
            } else if (opcion == 11) {
                modelIn.menuModel();
            } else if (opcion == 12) {
//                statusIn.crudStatus();
            } else if (opcion == 7) {
//                roleIn.crudRole();
            } else if (opcion == 15) {
                break menuAdmin;
            } else if (opcion == 5) {
//                employeeIn.crudEmployee();
            } else if (opcion == 6) {
//                tripCrewIn.crew();
            } else if (opcion == 8) {
//                fareIn.crudFares();
            } else if (opcion == 9) {
//                documentIn.crudDocument();
            } else {
                System.out.println("Opción incorrecta");
            }
        }
    }

    public void optionsSalesAgent(){
        salesAgent: while (true){
            System.out.println("+-------------------------------+");
            System.out.println("|     MENU DE AGENTE DE VENTAS  |");
            System.out.println("+-------------------------------+");
            System.out.printf("| %-2d. %-24s |\n", 1, "Gestionar los clientes");
            System.out.printf("| %-2d. %-24s |\n", 2, "Gestionar reservas");
            System.out.printf("| %-2d. %-24s |\n", 3, "Regresar");
            System.out.println("+-----------------------------+");
            int opcion = validaciones.validarInt("Seleccione una opción: ");
            if (opcion == 1){
//                customerIn.crudCustomer();
            } else if (opcion == 2) {
//                flightResIn.crudFlightRes();
            } else if (opcion == 3) {
                break salesAgent;
            }else {
                System.out.println("Opción inválida");
            }
        }
    }

}

