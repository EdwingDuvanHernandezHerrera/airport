package org.vuelosGlobales.revision.adapter.in;

import org.vuelosGlobales.employee.domain.EmployeeRelationshipDTO;
import org.vuelosGlobales.revision.application.RevisionService;
import org.vuelosGlobales.revision.domain.Revision;
import org.vuelosGlobales.revision.domain.RevisionInfoDTO;
import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.airline.domain.Airline;
import org.vuelosGlobales.plane.domain.PlaneStMdDTO;

import java.sql.Date;
import java.util.List;

public class RevisionConsoleAdapter {
    private final RevisionService revisionService;
    Validaciones validaciones = new Validaciones();

    public RevisionConsoleAdapter(RevisionService revisionService) {
        this.revisionService = revisionService;
    }
    public  void crudRevision(){
        menuRevision: while (true){
            System.out.println("------- Menú de Gestión de Revisiones -------");
            System.out.println("1. Crear Revisión");
            System.out.println("2. Actualizar Revisión");
            System.out.println("3. Buscar Revisión por avión");
            System.out.println("4. Eliminar Revisión");
            System.out.println("5. Salir");
            System.out.println("--------------------------------------------");
            int choice = validaciones.validarInt("Seleccione una opción del menú");

            switch (choice){
                case 1:
                    showAirlines();
                    Airline airline = validaciones.validarExistId(
                            () -> revisionService.getAirlineById(validaciones.validarInt("Seleccione la aerolínea a la que pertenece el avión: "))
                    );
                    int idAirline = airline.getId();

                    showPlanes(true, idAirline);
                    PlaneStMdDTO getPlane = validaciones.validarExistId(
                            () -> revisionService.getPlaneById(validaciones.validarInt("Seleccione el avión al que le va a hacer la revisión: "))
                    );
                    int idPlane = getPlane.getId();

                    System.out.println("Listado de empleados de la aerolínea " + airline.getName());
                    showEmployees(true, airline.getId());
                    EmployeeRelationshipDTO employee = validaciones.validarExistId(
                            () -> revisionService.getEmployeeById(validaciones.validarString("Seleccione al empleado por el id: ").toUpperCase())
                    );
                    String idEmployee = employee.getId();


                    Date revisionDate = validaciones.validarFecha("En que fecha se hizo la revisión, formato valido de fecha(YYYY-MM-DD): ");
                    String description = validaciones.validarString("Escriba una descripción detallada de la revisión:\n ");
                    Revision revision = new Revision();
                    revision.setRevisionDate(revisionDate);
                    revision.setIdPlane(idPlane);
                    revision.setDescription(description);
                    int id = revisionService.createRevision(revision);
                    revisionService.saveRevisionEmpl(id, idEmployee);
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    showRevisions();
                    Revision revisionSelect = validaciones.validarExistId(
                            () -> revisionService.getRevisionById(validaciones.validarInt("Seleccione la revisión  por el id: "))
                    );
                    System.out.println(revisionSelect);
                    Date revisionDateUp = validaciones.validarFecha("Ingrese al fecha actualizada de la revisión: ");
                    String descriptionUp = validaciones.validarString("Escriba la nueva descripción: \n");
                    int idPlaneRevision;
                    String validate = validaciones.validarString("Quiere cambiar el avión al que le hizo la revisión? (y/n)");
                    if (validate.equals("y")){
                        showPlanes(false, 0);
                        PlaneStMdDTO getPlaneSelect = validaciones.validarExistId(
                                () -> revisionService.getPlaneById(validaciones.validarInt("A que país pertenece la ciudad, seleccione por el por el id: "))
                        );
                        idPlaneRevision = getPlaneSelect.getId();
                    }else {
                        idPlaneRevision = revisionSelect.getIdPlane();
                    }
                    revisionSelect.setRevisionDate(revisionDateUp);
                    revisionSelect.setDescription(descriptionUp);
                    revisionSelect.setIdPlane(idPlaneRevision);
                    revisionService.updateRevision(revisionSelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    showAirlines();
                    Airline airlineSelect = validaciones.validarExistId(
                            () -> revisionService.getAirlineById(validaciones.validarInt("Seleccione la aerolínea a la que pertenece el avión: "))
                    );
                    System.out.println();
                    showPlanes(true, airlineSelect.getId());
                    PlaneStMdDTO showPlanes = validaciones.validarExistId(
                            () -> revisionService.getPlaneById(validaciones.validarInt("Seleccionar el avión por su id: "))
                    );

                    showRevisionByIdPlane(showPlanes.getId());
                    break;

                case 4:
                    showAirlines();
                    Airline airlineSel = validaciones.validarExistId(
                            () -> revisionService.getAirlineById(validaciones.validarInt("Seleccione la aerolínea a la que pertenece el avión: "))
                    );
                    System.out.println();
                    showPlanes(true, airlineSel.getId());
                    PlaneStMdDTO planesS = validaciones.validarExistId(
                            () -> revisionService.getPlaneById(validaciones.validarInt("Seleccionar el avión por su id: "))
                    );

                    showRevisionByIdPlane(planesS.getId());
                    Revision rev = validaciones.validarExistId(
                            () -> revisionService.getRevisionById(validaciones.validarInt("Seleccione por el id la revisión a eliminar: "))
                    );
                    int revisionDelete = rev.getId();
                    revisionService.deleteRevision(revisionDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;
                case 5:
                    break menuRevision;
            }
        }
    }

    public void showRevisions(){
        List<Revision> revisionList = revisionService.getAllRevisions();
        System.out.println(String.format("\n| %-4s | %-16s | %-16s | %-16s ", "ID", "FECHA", "AVIÓN", "DESCRIPCIÓN"));
        revisionList.forEach(revision -> {
            System.out.println(String.format("\n| %-4s | %-16s | %-16s | %-16s ", revision.getId(), revision.getRevisionDate(), revision.getIdPlane(), revision.getDescription()));
        });
    }

    public void showAirlines(){
        List<Airline> airlineList = revisionService.getAllAirlines();
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        airlineList.forEach(airline -> {
            System.out.println(String.format("\n| %-4s | %-16s |", airline.getId(), airline.getName()));
        });
    }

    public void showPlanes(boolean filter, int id){
        List<PlaneStMdDTO> planeList = revisionService.getAllPlanes(filter, id);
        System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-20s | %-20s |", "ID", "PLACA", "CAPACIDAD", "FECHAFAB", "ESTADO", "MODELO"));
        planeList.forEach(plane -> {
            System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-20s | %-20s |", plane.getId(), plane.getPlates(), plane.getCapacity(), plane.getFabricationDate(), plane.getNameStatus(), plane.getNameModel()));
        });
    }

    public void showRevisionByIdPlane(int id){
        List<RevisionInfoDTO> revisionList = revisionService.getRevisionInfo(id);
        System.out.println(String.format("\n| %-4s | %-16s | %-25s | %-15s | %-15s | %-40s ", "ID", "FECHA", "EMPLEADO", "PLACA", "MODELO", "DESCRIPCIÓN"));
        revisionList.forEach(revision -> {
            System.out.println(String.format("\n| %-4s | %-16s | %-25s | %-15s | %-15s | %-40s ", revision.getId(), revision.getRevisionDate(), revision.getNameEmployee(), revision.getPlatePlane(), revision.getModelPlane(), revision.getDescription()));
        });
    }

    public void showEmployees(boolean filter, int id){
        List<EmployeeRelationshipDTO> employeeList = revisionService.getAllEployeesInfo(filter, id);
        System.out.println(String.format("\n| %-11s | %-17s | %-16s | %-45s |", "ID", "NOMBRE", "ROL", "AIRPORT"));
        employeeList.forEach(employee -> {
            System.out.println(String.format("\n| %-11s | %-17s | %-16s | %-45s |", employee.getId(), employee.getName(), employee.getRolName(), employee.getAirportName()));
        });
    }
}
