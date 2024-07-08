package org.vuelosGlobales.document.infrastructure;

import org.vuelosGlobales.document.domain.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository {
    void save(Document document);
    void update(Document document);
    Optional<Document> findById(int id);
    List<Document> findAll();
    void delete(int id);
}
