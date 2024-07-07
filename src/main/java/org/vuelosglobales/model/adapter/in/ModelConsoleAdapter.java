package org.vuelosglobales.model.adapter.in;

import org.vuelosglobales.helpers.Validaciones;
import org.vuelosglobales.manufacturer.domain.Manufacturer;
import org.vuelosglobales.model.application.ModelService;
import org.vuelosglobales.model.domain.Model;
import org.vuelosglobales.model.domain.ModelManufacturerDTO;

import java.util.List;

public class ModelConsoleAdapter {
    private final ModelService modelService;

    public ModelConsoleAdapter(ModelService modelService) {
        this.modelService = modelService;
    }

    public void menuModel() {
        Validaciones validacion = new Validaciones();

        menuModelo:while (true) {

            System.out.println("1. Guardar modelo");
            System.out.println("2. Actualizar modelo");
            System.out.println("3. Eliminar modelo");
            System.out.println("4. Listar modelos");
            System.out.println("5. Salir");
            int option = validacion.validarInt("Seleccione una opciòn del menú");
    
            switch (option) {
                case 1:
                    String name = validacion.validarString("Ingrese el nombre del modelo");
                    Model model = new Model();
                    model.setName(name);

                    List<Manufacturer> manufacturers = modelService.findAllManufacturer();
                    manufacturers.forEach(registro -> System.out.println(registro));
                    Manufacturer manufacturerSelect = validacion.validarExistId(
                            () -> modelService.findManufacturerById(validacion.validarInt("Seleccione el id del fabricante del nuevo modelo"))
                    );

                    model.setManufacturerId(manufacturerSelect.getId());
                    modelService.saveModel(model);
                    break;
                case 2:
                    List<Model> models = modelService.findAllModel();
                    models.forEach(registro -> System.out.println(registro));
                    System.out.println();
                    Model modelUpdate = validacion.validarExistId(
                            () -> modelService.findByIdModel(validacion.validarInt("Seleccione el id del modelo que desea actualizar:\n"))
                    );
                    String newName = validacion.validarString("Ingrese el nuevo nombre del modelo:\n");

                    String question = validacion.yesOrNo("¿Desea actualizar cambiar el fabricante del modelo?(y/n)");

                    int manuIdNew;
                    if(question.equals("y")) {
                        List<Manufacturer> manufacturers2 = modelService.findAllManufacturer();
                        manufacturers2.forEach(registro -> System.out.println(registro));
                        Manufacturer manufacturerNew = validacion.validarExistId(
                                () -> modelService.findManufacturerById(validacion.validarInt("Seleccione el id del fabricante del modelo"))
                        );

                        manuIdNew = manufacturerNew.getId();
                    }else {
                        manuIdNew = modelUpdate.getManufacturerId();
                    }
                    modelService.updateModel(new Model(modelUpdate.getId(), newName, manuIdNew));
                    System.out.println("El modelo ha sido actualizado\n");
                    break;
                case 3:
                    List<ModelManufacturerDTO> modelsDeleteDTO = modelService.findAllModelManufacturerDTO();
                    modelsDeleteDTO.forEach(registro -> {

                        System.out.println(String.format("| %-10s | %-20s | %-20s", registro.getId(), registro.getName(), registro.getNameManufacturer()));
                    });
                    Model modelDelete = validacion.validarExistId(
                            () -> modelService.findByIdModel(validacion.validarInt("Seleccione el id del modelo que desea eliminar"))
                    );
                    modelService.deleteModel(modelDelete.getId());
                    System.out.println("El modelo" + modelDelete.getName() + "ha sido eliminado");
                    break;

                case 4:
                    List<ModelManufacturerDTO> modelsListDTO = modelService.findAllModelManufacturerDTO();
                    System.out.println(String.format("| %-10s | %-20s | %-20s", "ID", "Name Model", "Name Fabricante"));
                    modelsListDTO.forEach(registro -> {

                        System.out.println(String.format("| %-10s | %-20s | %-20s", registro.getId(), registro.getName(), registro.getNameManufacturer()));
                    });
                    System.out.println();
                    break;
                case 5:
                    break menuModelo;

            }            

        }
    }

}
