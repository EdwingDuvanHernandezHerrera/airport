package org.vuelosGlobales.helpers;

import org.vuelosGlobales.customer.adapter.in.CustomerConsoleAdapter;
import org.vuelosGlobales.customer.adapter.out.CustomerMySQLRepository;
import org.vuelosGlobales.customer.application.CustomerService;

import org.vuelosGlobales.manufacturer.adapter.in.ManufacturerConsoleAdapter;
import org.vuelosGlobales.manufacturer.adapter.out.ManufacturerMYSQLRepository;
import org.vuelosGlobales.manufacturer.application.ManufacturerService;

import org.vuelosGlobales.model.adapter.in.ModelConsoleAdapter;
import org.vuelosGlobales.model.adapter.out.ModelMYSQLRepository;
import org.vuelosGlobales.model.application.ModelService;

import org.vuelosGlobales.status.adapter.out.StatusMySQLRepository;
import org.vuelosGlobales.status.adapter.in.StatusConsoleAdapter;
import org.vuelosGlobales.status.application.StatusService;

import org.vuelosGlobales.airline.adapter.out.AirlineMySQLRepository;
import org.vuelosGlobales.airline.adapter.in.AirlineConsoleAdap;
import org.vuelosGlobales.airline.application.AirlineService;

import org.vuelosGlobales.plane.adapter.out.PlaneMySQLRepository;
import org.vuelosGlobales.plane.adapter.in.PlaneConsoleAdapter;
import org.vuelosGlobales.plane.application.PlaneService;

import org.vuelosGlobales.country.adapter.out.CountryMySQLRepository;
import org.vuelosGlobales.country.adapter.in.CountryConsoleAdapter;
import org.vuelosGlobales.country.application.CountryService;

import org.vuelosGlobales.city.adapter.out.CityMySQLRepository;
import org.vuelosGlobales.city.adapter.in.CityConsoleAdapter;
import org.vuelosGlobales.city.application.CityService;

import org.vuelosGlobales.airport.adapter.out.AirportMySQLRepository;
import org.vuelosGlobales.airport.adapter.in.AirportConsoleAdapter;
import org.vuelosGlobales.airport.application.AirportService;

import org.vuelosGlobales.role.adapter.in.RoleConsoleAdap;
import org.vuelosGlobales.role.adapter.out.RoleMySQLRepository;
import org.vuelosGlobales.role.application.RoleService;

import org.vuelosGlobales.employee.adapter.in.EmployeeConsoleAdap;
import org.vuelosGlobales.employee.adapter.out.EmployeeMySQLRepository;
import org.vuelosGlobales.employee.application.EmployeeService;

import org.vuelosGlobales.revision.adapter.in.RevisionConsoleAdapter;
import org.vuelosGlobales.revision.adapter.out.RevisionMySQLRepository;
import org.vuelosGlobales.revision.application.RevisionService;

import org.vuelosGlobales.document.adapter.in.DocumentConsoleAdapter;
import org.vuelosGlobales.document.adapter.out.DocumentMySQLRepository;
import org.vuelosGlobales.document.application.DocumentService;

import org.vuelosGlobales.fare.adapter.in.FareConsoleAdapter;
import org.vuelosGlobales.fare.adapter.out.FareMySQLRepository;
import org.vuelosGlobales.fare.application.FareService;

import org.vuelosGlobales.connection.adapter.out.ConnectionMySQLRepository;

import org.vuelosGlobales.trip.adapter.in.TripConsoleAdapter;
import org.vuelosGlobales.trip.adapter.out.TripMySQLRepository;
import org.vuelosGlobales.trip.application.TripService;

import org.vuelosGlobales.tripcrew.adapter.in.TripCrewConsoleAdap;
import org.vuelosGlobales.tripcrew.adapter.out.TripCrewMySQLRepository;
import org.vuelosGlobales.tripcrew.application.TripCrewService;



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

    StatusMySQLRepository statusOut = new StatusMySQLRepository(url, user, password);
    StatusService statusService = new StatusService(statusOut);
    StatusConsoleAdapter statusIn = new StatusConsoleAdapter(statusService);

    AirlineMySQLRepository airlineOut = new AirlineMySQLRepository(url, user, password);
    AirlineService airlineService = new AirlineService(airlineOut);
    AirlineConsoleAdap airlineIn = new AirlineConsoleAdap(airlineService);

    PlaneMySQLRepository planeOut = new PlaneMySQLRepository(url, user, password);
    PlaneService planeService = new PlaneService(planeOut, statusOut, modelOut, airlineOut);
    PlaneConsoleAdapter planeIn = new PlaneConsoleAdapter(planeService);

    CountryMySQLRepository countryOut = new CountryMySQLRepository(url, user, password);
    CountryService countryService = new CountryService(countryOut);
    CountryConsoleAdapter countryIn = new CountryConsoleAdapter(countryService);

    CityMySQLRepository cityOut = new CityMySQLRepository(url, user, password);
    CityService cityService = new CityService(cityOut, countryOut);
    CityConsoleAdapter cityIn = new CityConsoleAdapter(cityService);

    AirportMySQLRepository airportOut = new AirportMySQLRepository(url, user, password);
    AirportService airportService = new AirportService(airportOut, cityOut);
    AirportConsoleAdapter airportIn = new AirportConsoleAdapter(airportService);

    RoleMySQLRepository roleOut = new RoleMySQLRepository(url, user, password);
    RoleService roleService = new RoleService(roleOut);
    RoleConsoleAdap roleIn = new RoleConsoleAdap(roleService);

    EmployeeMySQLRepository employOut = new EmployeeMySQLRepository(url, user, password);
    EmployeeService employService = new EmployeeService(employOut, roleOut, airlineOut, airportOut);
    EmployeeConsoleAdap employeeIn = new EmployeeConsoleAdap(employService);

    RevisionMySQLRepository revisionOut = new RevisionMySQLRepository(url, user, password);
    RevisionService revisionService = new RevisionService(revisionOut, planeOut, airlineOut, employOut);
    RevisionConsoleAdapter revisionIn = new RevisionConsoleAdapter(revisionService);

    DocumentMySQLRepository documentOut = new DocumentMySQLRepository(url, user, password);
    DocumentService documentService = new DocumentService(documentOut);
    DocumentConsoleAdapter documentIn = new DocumentConsoleAdapter(documentService);

    FareMySQLRepository fareOut = new FareMySQLRepository(url, user, password);
    FareService fareService = new FareService(fareOut);
    FareConsoleAdapter fareIn = new FareConsoleAdapter(fareService);

    CustomerMySQLRepository customerOut = new CustomerMySQLRepository(url, user, password);
    CustomerService customerService = new CustomerService(customerOut, documentOut);
    CustomerConsoleAdapter customerIn = new CustomerConsoleAdapter(customerService);

    ConnectionMySQLRepository connectionOut = new ConnectionMySQLRepository(url, user, password);

    TripMySQLRepository tripOut = new TripMySQLRepository(url, user, password);
    TripService tripService = new TripService(tripOut, airportOut, planeOut, connectionOut);
    TripConsoleAdapter tripIn = new TripConsoleAdapter(tripService);

    TripCrewMySQLRepository tripCrewOut = new TripCrewMySQLRepository(url, user, password);
    TripCrewService tripCrewService = new TripCrewService(tripCrewOut, airlineOut, employOut, connectionOut, tripOut);
    TripCrewConsoleAdap tripCrewIn = new TripCrewConsoleAdap(tripCrewService);

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
                revisionIn.crudRevision();
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
                planeIn.crudPlane();
            } else if (opcion == 2){
                airportIn.crudAirport();
            } else if (opcion == 3){
                airlineIn.crudAirline();
            } else if (opcion == 4) {
                tripIn.crudTrip();
            } else if (opcion == 13) {
                countryIn.crudCountry();
            } else if (opcion == 14) {
                cityIn.crudCity();
            } else if (opcion == 10) {
                manufacturerIn.menuManufacturer();
            } else if (opcion == 11) {
                modelIn.menuModel();
            } else if (opcion == 12) {
                statusIn.crudStatus();
            } else if (opcion == 7) {
                roleIn.crudRole();
            } else if (opcion == 15) {
                break menuAdmin;
            } else if (opcion == 5) {
                employeeIn.crudEmployee();
            } else if (opcion == 6) {
                tripCrewIn.crew();
            } else if (opcion == 8) {
                fareIn.crudFares();
            } else if (opcion == 9) {
                documentIn.crudDocument();
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
                customerIn.crudCustomer();
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

