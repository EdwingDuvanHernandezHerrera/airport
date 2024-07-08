package org.vuelosGlobales.customer.adapter.in;

import org.vuelosGlobales.customer.domain.Customer;
import org.vuelosGlobales.customer.application.CustomerService;
import org.vuelosGlobales.customer.domain.CustomerDocuDTO;
import org.vuelosGlobales.document.domain.Document;
import org.vuelosGlobales.helpers.Validaciones;

import java.util.List;

public class CustomerConsoleAdapter {
    private final CustomerService customerService;
    Validaciones validaciones = new Validaciones();

    public CustomerConsoleAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }

    public  void crudCustomer(){
        menuCustomer: while (true){
            System.out.println("======================================");
            System.out.println("            MENÚ DE CLIENTES          ");
            System.out.println("======================================");
            System.out.println("\t1. Crear Cliente");
            System.out.println("\t2. Actualizar Cliente");
            System.out.println("\t3. Buscar Cliente por ID");
            System.out.println("\t4. Eliminar Cliente");
            System.out.println("\t5. Listar todas las Clientes");
            System.out.println("\t6. Salir");
            System.out.println("======================================");
            int choice = validaciones.validarInt("");

            switch (choice){
                case 1:
                    String name = validaciones.validarString("Nombre del cliente: ");
                    String lastName = validaciones.validarString("Apellidos del cliente: ");
                    int age = validaciones.validarInt("Ingrese la edad del cliente: ");
                    int docu = validaciones.validarInt("Ingrese el numero de identificacion del cliente: ");
                    showDocuments();
                    Document getDocument = validaciones.validarExistId(
                            () -> customerService.getDocumentById(validaciones.validarInt("Seleccione el documento por el ID: "))
                    );
                    Customer customer = new Customer();
                    customer.setName(name);
                    customer.setLastName(lastName);
                    customer.setAge(age);
                    customer.setNroId(docu);
                    customer.setIdDocument(getDocument.getId());
                    customerService.createCustomer(customer);
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    showCustomers();
                    Customer customerSelect = validaciones.validarExistId(
                            () -> customerService.getCustomerById(validaciones.validarInt("Seleccione la cliente por el id: "))
                    );
                    String newName;
                    String v1 = validaciones.validarString("Quiere cambiar el nombre del cliente? (s/n): ");
                    if (v1.equals("s")){
                        newName = validaciones.validarString("Ingrese el nuevo nombre: ");
                    }else {
                        newName = customerSelect.getName();
                    }

                    String newLastname;
                    String v2 = validaciones.validarString("Quiere cambiar el apellido del cliente? (s/n): ");
                    if (v2.equals("s")){
                        newLastname = validaciones.validarString("Ingrese el nuevo apellido: ");
                    }else {
                        newLastname = customerSelect.getLastName();
                    }

                    int newNro;
                    String v3 = validaciones.validarString("Quiere cambiar el numero de identificacion del cliente? (s/n): ");
                    if (v3.equals("s")){
                        newNro = validaciones.validarInt("Ingrese el nuevo numero de identificacion: ");
                    }else {
                        newNro = customerSelect.getNroId();
                    }

                    int newAge;
                    String v4 = validaciones.validarString("Quiere cambiar la edad del cliente? (s/n): ");
                    if (v4.equals("s")){
                        newAge = validaciones.validarInt("Ingrese el nueva edad del cliente: ");
                    }else {
                        newAge = customerSelect.getAge();
                    }

                    int idDocument;
                    String validate = validaciones.validarString("Quiere cambiar el tipo de documento del cliente? (s/n): ");
                    if (validate.equals("s")){
                        showDocuments();
                        Document getObj = validaciones.validarExistId(
                                () -> customerService.getDocumentById(validaciones.validarInt("A que país pertenece la cliente, seleccione por el por el id: "))
                        );
                        idDocument = getObj.getId();
                    }else {
                        idDocument = customerSelect.getIdDocument();
                    }
                    customerSelect.setName(newName);
                    customerSelect.setLastName(newLastname);
                    customerSelect.setNroId(newNro);
                    customerSelect.setAge(newAge);
                    customerSelect.setIdDocument(idDocument);
                    customerService.updateCustomer(customerSelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    showCustomers();
                    CustomerDocuDTO showCustomer = validaciones.validarExistId(
                            () -> customerService.getCusDocById(validaciones.validarInt("Seleccione la cliente por el id: "))
                    );

                    System.out.printf("\n| %-6s | %-20s | %-20s | %-20s | %-6s | %-20s |%n", "ID", "NOMBRE", "APELLIDOS", "#ID", "EDAD", "DOCU");
                    System.out.printf("\n| %-6s | %-20s | %-20s | %-20s | %-6s | %-20s |%n", showCustomer.getId(), showCustomer.getNameCustomer(), showCustomer.getLastName(), showCustomer.getNroId(), showCustomer.getAge(), showCustomer.getNameDocument());
                    System.out.println();
                    break;

                case 4:
                    showCustomers();
                    Customer showCustomerD = validaciones.validarExistId(
                            () -> customerService.getCustomerById(validaciones.validarInt("Seleccione la cliente por el id: "))
                    );
                    int customerDelete = showCustomerD.getId();
                    customerService.deleteCustomer(customerDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;
                case 5:
                    showCustomers();
                    System.out.println();
                    break;
                case 6:
                    break menuCustomer;
            }
        }
    }

    public void showCustomers(){
        List<CustomerDocuDTO> customerList = customerService.getAllCustDoc();
        System.out.printf("\n| %-6s | %-20s | %-20s | %-20s | %-6s | %-20s |%n", "ID", "NOMBRE", "APELLIDOS", "#ID", "EDAD", "DOCU");
        customerList.forEach(customer -> {
            System.out.println(String.format("\n| %-6s | %-20s | %-20s | %-20s | %-6s | %-20s |", customer.getId(), customer.getNameCustomer(), customer.getLastName(), customer.getNroId(), customer.getAge(), customer.getNameDocument()));
        });
    }

    public void showDocuments(){
        List<Document> documents = customerService.getAllDocuments();
        System.out.printf("\n| %-4s | %-18s |%n", "ID", "NOMBRE");
        documents.forEach(document -> {
            System.out.printf("\n| %-4s | %-18s |%n", document.getId(), document.getName());
        });
    }
}
