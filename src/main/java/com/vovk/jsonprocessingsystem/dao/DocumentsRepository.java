package com.vovk.jsonprocessingsystem.dao;

import com.arangodb.springframework.repository.ArangoRepository;
import com.vovk.jsonprocessingsystem.model.JsonDocument;

/**
 * @author Dmytro Vovk
 */

public interface DocumentsRepository extends ArangoRepository<JsonDocument> {

}
