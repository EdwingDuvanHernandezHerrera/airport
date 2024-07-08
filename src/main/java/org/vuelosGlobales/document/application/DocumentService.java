package org.vuelosGlobales.document.application;

import org.vuelosGlobales.document.domain.Document;
import org.vuelosGlobales.document.infrastructure.DocumentRepository;

import java.util.List;
import java.util.Optional;

public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void createDocument(Document document){
        this.documentRepository.save(document);
    }

    public void updateDocument(Document document){
        this.documentRepository.update(document);
    }

    public Optional<Document> getDocumentById(int id){
        return this.documentRepository.findById(id);
    }

    public List<Document> getAllDocuments(){
        return this.documentRepository.findAll();
    }

    public void deleteDocument(int id){
        this.documentRepository.delete(id);
    }
}
