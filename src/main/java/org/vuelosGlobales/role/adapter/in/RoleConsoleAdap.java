package org.vuelosGlobales.role.adapter.in;

import org.vuelosGlobales.role.domain.Role;
import org.vuelosGlobales.role.application.RoleService;
import org.vuelosGlobales.helpers.Validaciones;

import java.util.List;

public class RoleConsoleAdap {
    private final RoleService roleService;
    Validaciones validaciones = new Validaciones();
    public RoleConsoleAdap(RoleService roleService) {
        this.roleService = roleService;
    }

    public  void crudRole(){
        menuRole: while (true){
            System.out.println("1. Crear Rol");
            System.out.println("2. Actualizar Rol");
            System.out.println("3. Buscar Rol por ID");
            System.out.println("4. Eliminar Rol");
            System.out.println("5. Listar todos Roles");
            System.out.println("6. Salir");
            int choice = validaciones.validarInt("Seleccione una opción del menú: ");

            switch (choice){
                case 1:
                    String name = validaciones.validarString("Nombre del rol: ");
                    Role role = new Role();
                    role.setName(name);
                    roleService.createRole(role);
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    showRoles();
                    Role roleSelect = validaciones.validarExistId(
                            () -> roleService.getRoleById(validaciones.validarInt("Seleccione el rol por el id: "))
                    );
                    String newName = validaciones.validarString("Nuevo nombre del rol: ");
                    roleSelect.setName(newName);
                    roleService.updateRole(roleSelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    showRoles();
                    Role showRole = validaciones.validarExistId(
                            () -> roleService.getRoleById(validaciones.validarInt("Seleccione el rol por el id: "))
                    );
                    System.out.println(showRole);
                    break;

                case 4:
                    showRoles();
                    Role showRoleF = validaciones.validarExistId(
                            () -> roleService.getRoleById(validaciones.validarInt("Seleccione el rol por el id: "))
                    );
                    int roleDelete = showRoleF.getId();
                    roleService.deleteRole(roleDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;
                case 5:
                    showRoles();
                    break;
                case 6:
                    break menuRole;
            }
        }
    }

    public void showRoles(){
        List<Role> roleList = roleService.getAllRoles();
        System.out.println("Listado de roles:");
        System.out.println(String.format("\n| %-4s | %-16s |", "ID", "NOMBRE"));
        roleList.forEach(role -> {
            System.out.println(String.format("\n| %-4s | %-16s |", role.getId(), role.getName()));
        });
        System.out.println();
    }
}
