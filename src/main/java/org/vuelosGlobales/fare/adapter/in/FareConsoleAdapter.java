package org.vuelosGlobales.fare.adapter.in;

import org.vuelosGlobales.country.domain.Country;
import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.fare.application.FareService;
import org.vuelosGlobales.fare.domain.Fare;

import java.util.List;

public class FareConsoleAdapter {
    private final FareService fareService;
    Validaciones validaciones = new Validaciones();
    public FareConsoleAdapter(FareService fareService) {
        this.fareService = fareService;
    }

    public  void crudFares(){
        menuCountry: while (true){
            System.out.println("======================================");
            System.out.println("             MENÚ DE TARIFAS           ");
            System.out.println("======================================");
            System.out.println("\t1. Crear taifa de vuelo");
            System.out.println("\t2. Actualizar tarifa de vuelo");
            System.out.println("\t3. Buscar tarifa por ID");
            System.out.println("\t4. Eliminar tarifa");
            System.out.println("\t5. Salir");
            System.out.println("======================================");
            int choice = validaciones.validarInt("");

            switch (choice){
                case 1:
                    String description = validaciones.validarString("Descripcion de la tarifa: ");
                    String detail = validaciones.validarString("Detalle de la tarifa: ");
                    Double value = Double.parseDouble(validaciones.validarString("Valor de la tarifa: "));

                    Fare fare = new Fare();
                    fare.setDescription(description);
                    fare.setDetails(detail);
                    fare.setValue(value);
                    fareService.createFare(fare);
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    showFares();
                    Fare fareSelect = validaciones.validarExistId(
                            () -> fareService.getFareById(validaciones.validarInt("Seleccione el pais por el id: "))
                    );
                    String newDescription;
                    String v1 = validaciones.validarString("Quiere actulizar la descripcion de la tarifa?: (s/n): ");
                    if (v1.equals("s")){
                        newDescription = validaciones.validarString("Ingrese la nueva descripcion de la tarifa: ");
                    }else {
                        newDescription = fareSelect.getDescription();
                    }
                    String newDetail;
                    String v2 = validaciones.validarString("Quiere actulizar el detalle de la tarifa?: (s/n): ");
                    if (v2.equals("s")){
                        newDetail = validaciones.validarString("Ingrese el nuevo detalle de la tarifa: ");
                    }else {
                        newDetail = fareSelect.getDetails();
                    }

                    double newValue;
                    String v3 = validaciones.validarString("Quiere actulizar el valor de la tarifa?: (s/n): ");
                    if (v3.equals("s")){
                        newValue = Double.parseDouble(validaciones.validarString("Ingrese el nuevo detalle de la tarifa: "));
                    }else {
                        newValue = fareSelect.getValue();
                    }


                    fareSelect.setDescription(newDescription);
                    fareSelect.setDetails(newDetail);
                    fareSelect.setValue(newValue);
                    fareService.updateFare(fareSelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    consultarTarifa();
                    break;

                case 4:
                    showFares();
                    Fare fareToDelete = validaciones.validarExistId(
                            () -> fareService.getFareById(validaciones.validarInt("Seleccione el pais por el id: "))
                    );
                    int fareDelete = fareToDelete.getId();
                    fareService.deleteFare(fareDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;
                case 5:
                    break menuCountry;
            }
        }
    }

    public void showFares(){
        List<Fare> fares = fareService.getAllFares();
        System.out.printf("\n| %-4s | %-20s | %-30s | %-10s | %n", "ID", "DESC", "DETALLES", "VALOR");
        fares.forEach(fare -> {
            System.out.printf("\n| %-4s | %-20s | %-30s | %-10s |%n", fare.getId(), fare.getDescription(), fare.getDetails(), fare.getValue());
        });
        System.out.println();
    }

    public void consultarTarifa(){
        showFares();
        Fare getFare = validaciones.validarExistId(
                () -> fareService.getFareById(validaciones.validarInt("Seleccione la tarifa de vuelo por el id: "))
        );
        System.out.printf("\n| %-4s | %-20s | %-30s | %-10s | %n", "ID", "DESC", "DETALLES", "VALOR");
        System.out.printf("\n| %-4s | %-20s | %-30s | %-10s |%n", getFare.getId(), getFare.getDescription(), getFare.getDetails(), getFare.getValue());
    }
}
