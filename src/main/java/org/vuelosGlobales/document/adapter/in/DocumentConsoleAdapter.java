package org.vuelosGlobales.document.adapter.in;

import org.vuelosGlobales.helpers.Validaciones;
import org.vuelosGlobales.document.application.DocumentService;
import org.vuelosGlobales.document.domain.Document;

import java.util.List;

public class DocumentConsoleAdapter {
    private final DocumentService documentService;
    Validaciones validaciones = new Validaciones();

    public DocumentConsoleAdapter(DocumentService documentService) {
        this.documentService = documentService;
    }

    public  void crudDocument(){
        menuDocument: while (true){
            System.out.println("1. Registrar documento");
            System.out.println("2. Actualizar info de un documento");
            System.out.println("3. Buscar tipo de documento por ID");
            System.out.println("4. Eliminar un tipo de documento");
            System.out.println("5. Listar todos los tipos de documentos");
            System.out.println("6. Salir");
            int choice = validaciones.validarInt("Seleccione una opción del menú:\n");

            switch (choice){
                case 1:
                    String name = validaciones.validarString("Nombre del tipo de documento: ");
                    documentService.createDocument(new Document(name));
                    System.out.println("Registro guardado con éxito\n");
                    break;

                case 2:
                    showDocuments();
                    Document documentSelect = validaciones.validarExistId(
                            () -> documentService.getDocumentById(validaciones.validarInt("Seleccione el tipo de documento por el id: "))
                    );
                    String newName = validaciones.validarString("Nuevo nombre del tipo de documento: ");
                    documentSelect.setName(newName);
                    documentService.updateDocument(documentSelect);
                    System.out.println("Registro actualizado con éxito\n");
                    break;

                case 3:
                    System.out.println();
                    showDocuments();
                    Document showDocument = validaciones.validarExistId(
                            () -> documentService.getDocumentById(validaciones.validarInt("Seleccione el documento por el id: "))
                    );
                    System.out.println(showDocument);
                    break;

                case 4:
                    showDocuments();
                    Document showDocumentF = validaciones.validarExistId(
                            () -> documentService.getDocumentById(validaciones.validarInt("Seleccione el tipo de documento por el id: "))
                    );
                    int documentDelete = showDocumentF.getId();
                    documentService.deleteDocument(documentDelete);
                    System.out.println("Registro eliminado con éxito\n");
                    break;
                case 5:
                    showDocuments();
                    break;
                case 6:
                    break menuDocument;
            }
        }
    }

    public void showDocuments(){
        List<Document> documents = documentService.getAllDocuments();
        System.out.printf("\n| %-4s | %-20s |%n", "ID", "NOMBRE");
        documents.forEach(document -> {
            System.out.printf("\n| %-4s | %-20s |%n", document.getId(), document.getName());
        });
    }
}
