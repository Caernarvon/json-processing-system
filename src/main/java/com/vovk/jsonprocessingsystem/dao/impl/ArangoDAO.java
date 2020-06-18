package com.vovk.jsonprocessingsystem.dao.impl;

import com.arangodb.springframework.core.ArangoOperations;
import com.vovk.jsonprocessingsystem.dao.CRUD;
import com.vovk.jsonprocessingsystem.dao.DocumentsRepository;
import com.vovk.jsonprocessingsystem.model.JsonDocument;
import com.vovk.jsonprocessingsystem.model.exceptions.EntityNotFoundException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import static com.vovk.jsonprocessingsystem.constants.CommonConstants.DOCUMENTS_COLLECTION_PREFIX;
import static com.vovk.jsonprocessingsystem.constants.CommonConstants.ENTITY_NOT_FOUND_MESSAGE;

/**
 * @author Dmytro Vovk
 */

@ComponentScan("com.vovk.jsonprocessingsystem")
@Repository
public class ArangoDAO implements CRUD<JsonDocument> {

    private ArangoOperations operations;

    private DocumentsRepository documentsRepository;

    public ArangoDAO(ArangoOperations operations, DocumentsRepository documentsRepository) {
        this.operations = operations;
        this.documentsRepository = documentsRepository;
    }

    @Override
    public String create(JsonDocument entity) {
        documentsRepository.save(entity);
        return removePrefix(entity.getId());
    }

    @Override
    public void update(JsonDocument entity) {
        documentsRepository.save(entity);
    }

    @Override
    public void delete(String id) {
        documentsRepository.deleteById(id);
    }

    @Override
    public JsonDocument findById(String id) {
        return documentsRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
    }

    private String removePrefix(String uuid) {
        return uuid.replaceFirst(DOCUMENTS_COLLECTION_PREFIX, "");
    }
}
