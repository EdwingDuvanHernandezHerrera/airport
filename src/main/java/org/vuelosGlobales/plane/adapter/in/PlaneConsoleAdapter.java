package org.vuelosGlobales.plane.adapter.in;

import org.vuelosGlobales.model.domain.Model;
import org.vuelosGlobales.airline.domain.Airline;
import org.vuelosGlobales.plane.domain.Plane;
import org.vuelosGlobales.status.domain.Status;
import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.plane.application.PlaneService;
import org.vuelosGlobales.plane.domain.PlaneStMdDTO;

import java.sql.Date;
import java.util.List;

public class PlaneConsoleAdapter {
    private final PlaneService planeService;
    Validaciones validaciones = new Validaciones();
    public PlaneConsoleAdapter(PlaneService planeService) {
        this.planeService = planeService;
    }

    public  void crudPlane(){
        menuPlane: while (true){
            System.out.println("======================================");
            System.out.println("          MENÚ DE GESTIÓN DE AVIONES   ");
            System.out.println("======================================");
            System.out.println("\t1. Crear Avión");
            System.out.println("\t2. Actualizar Avión");
            System.out.println("\t3. Buscar Avión por ID");
            System.out.println("\t4. Eliminar Avión");
            System.out.println("\t5. Listar todos los Aviones");
            System.out.println("\t6. Salir");
            int choice = validaciones.validarInt("Seleccione una opción del menú");

            switch (choice){
                case 1:
                    showAirlines();
                    Airline airlineSelect = validaciones.validarExistId(
                            () -> planeService.getAirlineById(validaciones.validarInt("Seleccione la aerolínea a la que pertenece el avión"))
                    );
                    int airlineId = airlineSelect.getId();

                    String plate = validaciones.validarExistPlate("Ingrese la palca del avión",
                            placa -> planeService.validatePlate(placa)
                    );

                    int capacity = validaciones.validarInt("Cual es la capacidad del avión: ");
                    Date fabricationDate = validaciones.validarFecha("En que fecha se fabricó el avión (YYYY-MM-DD): ");


                    showModel();
                    Model getModel = validaciones.validarExistId(
                            () -> planeService.getModelById(validaciones.validarInt("Seleccione el modelo del avión: "))
                    );
                    int idModel = getModel.getId();

                    showStatus();
                    Status getStatus = validaciones.validarExistId(
                            () -> planeService.getStatusById(validaciones.validarInt("Seleccione el estado en que se encuentra el avión: "))
                    );
                    int idStatus = getStatus.getId();

                    Plane objPlane = new Plane();
                    objPlane.setPlates(plate);
                    objPlane.setCapacity(capacity);
                    objPlane.setFabricationDate(fabricationDate);
                    objPlane.setIdAirline(airlineId);
                    objPlane.setIdStatus(idStatus);
                    objPlane.setIdModel(idModel);
                    planeService.createPlane(objPlane);
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    showPlanes();
                    Plane planeSelect = validaciones.validarExistId(
                            () -> planeService.getPlaneById(validaciones.validarInt("Seleccione el avión por el id: "))
                    );

                    int updateCapacity = validaciones.validarInt("Nueva capacidad: ");

                    int idAirlineUp;
                    String validate1 = validaciones.yesOrNo("Quiere cambiar la aerolínea del avión? (y/n): ");
                    if (validate1.equals("y")){
                        showAirlines();
                        Airline getAirlineUp = validaciones.validarExistId(
                                () -> planeService.getAirlineById(validaciones.validarInt("Seleccione el nuevo estado en que se encuentra el avión: "))
                        );
                        idAirlineUp = getAirlineUp.getId();
                    }else {
                        idAirlineUp = planeSelect.getIdAirline();
                    }

                    int idStatusUp;
                    String validate = validaciones.yesOrNo("Quiere cambiar el estado del avión? (y/n): ");
                    if (validate.equals("y")){
                        showStatus();
                        Status getStatusUp = validaciones.validarExistId(
                                () -> planeService.getStatusById(validaciones.validarInt("Seleccione el nuevo estado en que se encuentra el avión: "))
                        );
                        idStatusUp = getStatusUp.getId();
                    }else {
                        idStatusUp = planeSelect.getIdStatus();
                    }
                    int idModelUp;
                    String validate2 = validaciones.yesOrNo("Quiere cambiar el modelo del avión? (y/n): ");
                    if (validate2.equals("y")){
                        showModel();
                        Model getModelUp = validaciones.validarExistId(
                                () -> planeService.getModelById(validaciones.validarInt("Seleccione el nuevo modelo en que se encuentra el avión: "))
                        );
                        idModelUp = getModelUp.getId();
                    }else {
                        idModelUp = planeSelect.getIdModel();
                    }

                    planeSelect.setCapacity(updateCapacity);
                    planeSelect.setIdAirline(idAirlineUp);
                    planeSelect.setIdStatus(idStatusUp);
                    planeSelect.setIdModel(idModelUp);
                    planeService.updatePlane(planeSelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    System.out.println();
                    showPlanes();
                    PlaneStMdDTO showPlane = validaciones.validarExistId(
                            () -> planeService.getPlaneStMdById(validaciones.validarInt("Seleccione el avión por el id: "))
                    );
                    System.out.println(showPlane);
                    System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-20s | %-20s | %-20s |", "ID", "PLACA", "CAPACIDAD", "FECHAFAB", "AEROLÍNEA", "ESTADO", "MODELO"));
                    System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-20s | %-20s | %-20s |", showPlane.getId(), showPlane.getPlates(), showPlane.getCapacity(), showPlane.getFabricationDate(),showPlane.getNameAirline(), showPlane.getNameStatus(), showPlane.getNameModel()));
                    System.out.println();
                    break;

                case 4:
                    showPlanes();
                    Plane showPlaneD = validaciones.validarExistId(
                            () -> planeService.getPlaneById(validaciones.validarInt("Seleccione el avión por el id: "))
                    );
                    int planeDelete = showPlaneD.getId();
                    planeService.deletePlane(planeDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;
                case 5:
                    showPlanes();
                    break;
                case 6:
                    break menuPlane;
            }
        }
    }

    public void showPlanes(){
        List<PlaneStMdDTO> planeList = planeService.getAllPlaneStMd(false, 0);
        System.out.println("Listado de aviones:");
        System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-25s | %-20s | %-20s |", "ID", "PLACA", "CAPACIDAD", "FECHAFAB", "AEROLÍNEA", "ESTADO", "MODELO"));
        planeList.forEach(plane -> {
            System.out.println(String.format("\n| %-4s | %-10s | %-10s | %-17s | %-25s | %-20s | %-20s |", plane.getId(), plane.getPlates(), plane.getCapacity(), plane.getFabricationDate(), plane.getNameAirline(), plane.getNameStatus(), plane.getNameModel()));
        });
        System.out.println();
    }

    public void showAirlines(){
        List<Airline> airlineList = planeService.getAllAirlines();
        System.out.println("Listado de aerolíneas:");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        airlineList.forEach(airline -> {
            System.out.println(String.format("\n| %-4s | %-16s |", airline.getId(), airline.getName()));
        });
        System.out.println();
    }
    public void showModel(){
        List<Model> modelList = planeService.getAllModels();
        System.out.println("Listado de modelos:");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        modelList.forEach(model -> {
            System.out.println(String.format("\n| %-4s | %-16s |", model.getId(), model.getName()));
        });
        System.out.println();
    }

    public void showStatus(){
        List<Status> statusList = planeService.getAllStatus();
        System.out.println("Listado de estados:");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        statusList.forEach(status -> {
            System.out.println(String.format("\n| %-4s | %-16s |", status.getId(), status.getName()));
        });
        System.out.println();
    }

}
