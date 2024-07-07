package org.vuelosGlobales.status.adapter.in;

import org.vuelosGlobales.model.domain.Model;
import org.vuelosGlobales.status.domain.Status;
import org.vuelosGlobales.status.application.StatusService;
import org.vuelosGlobales.helpers.Validaciones;

import java.util.List;

public class StatusConsoleAdapter {
    private final StatusService statusService;
    Validaciones validaciones = new Validaciones();

    public StatusConsoleAdapter(StatusService statusService) {
        this.statusService = statusService;
    }

    public void crudStatus(){
        menuStatus: while (true){
            System.out.println("======================================");
            System.out.println("         MENÚ DE GESTIÓN DE ESTADOS     ");
            System.out.println("======================================");
            System.out.println("\t1. Crear Estado");
            System.out.println("\t2. Actualizar Estado");
            System.out.println("\t3. Buscar Estado por ID");
            System.out.println("\t4. Eliminar Estado");
            System.out.println("\t5. Listar todos los Estados");
            System.out.println("\t6. Salir");
            int choice = validaciones.validarInt("Seleccione una opción del menú");

            switch (choice){
                case 1:
                    String name = validaciones.validarString("Nombre del estado: ");
                    Status st = new Status();
                    st.setName(name);
                    statusService.createStatus(st);
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    List<Status> statuses = statusService.getAllStatuss();
                    statuses.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    Status statusSelect = validaciones.validarExistId(
                            () -> statusService.getStatusById(validaciones.validarInt("Seleccione el id del estado a actualizar: "))
                    );
                    String newName = validaciones.validarString("Nuevo nombre del estado: ");
                    statusSelect.setName(newName);
                    statusService.updateStatus(statusSelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    List<Status> statuss = statusService.getAllStatuss();
                    statuss.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    Status showStatus = validaciones.validarExistId(
                            () -> statusService.getStatusById(validaciones.validarInt("Seleccione el estado por el id: "))
                    );
                    System.out.println(showStatus);
                    System.out.println();
                    break;

                case 4:
                    List<Status> statusesD = statusService.getAllStatuss();
                    statusesD.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    Status showStatusF = validaciones.validarExistId(
                            () -> statusService.getStatusById(validaciones.validarInt("Seleccione el estado por el id: "))
                    );
                    int statusDelete = showStatusF.getId();
                    statusService.deleteStatus(statusDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;

                case 5:
                    List<Status> statusesAll = statusService.getAllStatuss();
                    statusesAll.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    break;

                case 6:
                    break menuStatus;
            }
        }
    }

}

