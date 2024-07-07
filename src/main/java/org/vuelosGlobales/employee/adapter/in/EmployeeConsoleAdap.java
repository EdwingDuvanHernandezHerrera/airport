package org.vuelosGlobales.employee.adapter.in;

import org.vuelosGlobales.employee.application.EmployeeService;
import org.vuelosGlobales.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.role.domain.Role;
import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.airline.domain.Airline;
import org.vuelosGlobales.employee.domain.Employee;
import org.vuelosGlobales.airport.domain.AirportCityDTO;

import java.util.List;

public class EmployeeConsoleAdap {
    private final EmployeeService employeeService;
    Validaciones validaciones = new Validaciones();

    public EmployeeConsoleAdap(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public  void crudEmployee(){
        menuEmployee: while (true){
            System.out.println("======================================");
            System.out.println("          MENÚ DE GESTIÓN DE EMPLEADOS ");
            System.out.println("======================================");
            System.out.println("\t1. Crear empleado");
            System.out.println("\t2. Actualizar empleado");
            System.out.println("\t3. Buscar empleado por ID");
            System.out.println("\t4. Eliminar empleado");
            System.out.println("\t5. Listar todos los empleados");
            System.out.println("\t6. Salir");
            int choice = validaciones.validarInt("Seleccione una opción del menú");

            switch (choice){
                case 1:
                    String idEmployee = validaciones.validarExistPlate(
                            "Ingrese el id del empleado, debe tener máximo 5 caracteres: ",
                            id -> employeeService.getEmployeeById(id)
                    );
                    String employeeName = validaciones.validarString("Nombre del empleado: ");
                    String ingressDate = validaciones.validarString("En que fecha ingresó el empleado a la empresa: ");
                    showRoles();
                    Role roleSelect = validaciones.validarExistId(
                            () -> employeeService.getRoleById(validaciones.validarInt("Seleccione el rol del employee: "))
                    );
                    int idRol = roleSelect.getId();

                    showAirlines();
                    Airline airlineSelect = validaciones.validarExistId(
                            () -> employeeService.getAirlineById(validaciones.validarInt("Seleccione la aerolínea por su id: "))
                    );
                    int idAirline = airlineSelect.getId();
                    showAirportes();
                    AirportCityDTO airportSelect = validaciones.validarExistId(
                            () -> employeeService.getAirportById(validaciones.validarString("Ingrese el id del aeropuerto al que pertenece el empleado: ").toUpperCase())
                    );
                    String idAirport = airportSelect.getId();

                    Employee objEmployee = new Employee();
                    objEmployee.setId(idEmployee);
                    objEmployee.setNombre(employeeName);
                    objEmployee.setIngressDate(ingressDate);
                    objEmployee.setIdRol(idRol);
                    objEmployee.setIdAirline(idAirline);
                    objEmployee.setIdAirport(idAirport);
                    employeeService.createEmployee(objEmployee);
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    showEmployees();
                    Employee employeeSelect = validaciones.validarExistId(
                            () -> employeeService.getEmployeeById(validaciones.validarString("Seleccione el employee por el id: ").toUpperCase())
                    );

                    String nameUp = validaciones.validarString("Nueva nombre: ");
                    String ingressUp = validaciones.validarString("Nueva fecha de ingreso del empleado: ");

                    int idRolUp;
                    String validate1 = validaciones.validarString("Quiere cambiar el rol del employee? (y/n): ");
                    if (validate1.equals("y")){
                        showRoles();
                        Role roleSelectUp = validaciones.validarExistId(
                                () -> employeeService.getRoleById(validaciones.validarInt("Seleccione el rol del employee"))
                        );
                        idRolUp = roleSelectUp.getId();
                    }else {
                        idRolUp = employeeSelect.getIdRol();
                    }

                    int idAirlineUp;
                    String validate2 = validaciones.validarString("Quiere cambiar la aerolínea en la que trabaja el employee? (y/n): ");
                    if (validate2.equals("y")){
                        showAirlines();
                        Airline airlineSelectUp = validaciones.validarExistId(
                                () -> employeeService.getAirlineById(validaciones.validarInt("Seleccione la aerolínea por su id: "))
                        );
                        idAirlineUp = airlineSelectUp.getId();
                    }else {
                        idAirlineUp = employeeSelect.getIdAirline();
                    }

                    String idAirportUp;
                    String validate = validaciones.validarString("Quiere cambiar el aeropuerto base del empleado? (y/n): ");
                    if (validate.equals("y")){
                        showAirportes();
                        AirportCityDTO airportSelectUp = validaciones.validarExistId(
                                () -> employeeService.getAirportById(validaciones.validarString("Ingrese el id del aeropuerto al que pertenece el empleado").toUpperCase())
                        );
                        idAirportUp = airportSelectUp.getId();
                    }else {
                        idAirportUp = employeeSelect.getIdAirport();
                    }

                    employeeSelect.setNombre(nameUp);
                    employeeSelect.setIngressDate(ingressUp);
                    employeeSelect.setIdRol(idRolUp);
                    employeeSelect.setIdAirline(idAirlineUp);
                    employeeSelect.setIdAirport(idAirportUp);
                    employeeService.updateEmployee(employeeSelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    showEmployees();
                    EmployeeRelationshipDTO getEmployee = validaciones.validarExistId(
                            () -> employeeService.getEmployeeInfoById(validaciones.validarString("Seleccione el employee por el id: ").toUpperCase())
                    );
                    System.out.printf("\n| %-4s | %-18s | %-18s | %-18s | %-25s | %-30s |%n", "ID", "NOMBRE", "FECHA", "ROL", "AEROLÍNEA", "AIRPORT");
                    System.out.printf("\n| %-4s | %-18s | %-18s | %-18s | %-25s | %-30s |%n", getEmployee.getId(), getEmployee.getName(), getEmployee.getIngressDate(), getEmployee.getRolName(), getEmployee.getAirlineName(), getEmployee.getAirportName());
                    break;

                case 4:
                    showEmployees();
                    Employee showEmployeeD = validaciones.validarExistId(
                            () -> employeeService.getEmployeeById(validaciones.validarString("Seleccione el employee por el id: ").toUpperCase())
                    );
                    String employeeDelete = showEmployeeD.getId();
                    employeeService.deleteEmployee(employeeDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;
                case 5:
                    showEmployees();
                    System.out.println();
                    break;
                case 6:
                    break menuEmployee;
            }
        }
    }

    public void showEmployees(){
        List<EmployeeRelationshipDTO> employeeList = employeeService.getAllEmployeesInfo(false, 0);
        System.out.println("Listado de empleados:");
        System.out.printf("\n| %-11s | %-17s | %-15s | %-16s | %-28s | %-45s |%n", "ID", "NOMBRE", "FECHA", "ROL", "AEROLINEA", "AIRPORT");
        employeeList.forEach(employee -> {
            System.out.printf("\n| %-11s | %-17s | %-15s | %-16s | %-28s | %-45s |%n", employee.getId(), employee.getName(), employee.getIngressDate(), employee.getRolName(), employee.getAirlineName(), employee.getAirportName());
        });
    }

    public void showAirlines(){
        List<Airline> airlineList = employeeService.getAllAirlines();
        System.out.println("Listado de aerolíneas:");
        System.out.printf("\n| %-4s | %-16s |%n", "ID", "NOMBRE");
        airlineList.forEach(airline -> {
            System.out.printf("\n| %-4s | %-16s |%n", airline.getId(), airline.getName());
        });
    }

    public void showAirportes(){
        List<AirportCityDTO> airports = employeeService.getAllAirport();
        System.out.println("Listado de estados:");
        System.out.printf("\n| %-5s | %-22s | %-20s |%n", "ID", "NOMBRE", "CIUDAD");
        airports.forEach(airport -> {
            System.out.printf("\n| %-5s | %-22s | %-20s |%n", airport.getId(), airport.getNameAirport(), airport.getNameCity());
        });
    }

    public void showRoles(){
        List<Role> roleList = employeeService.getAllRoles();
        System.out.println("Listado de roles:");
        System.out.printf("\n| %-4s | %-16s |%n", "ID", "NOMBRE");
        roleList.forEach(role -> {
            System.out.printf("\n| %-4s | %-16s |%n", role.getId(), role.getName());
        });
    }
}
