package com.vovk.jsonprocessingsystem.services.impl;

import com.jayway.jsonpath.*;
import com.jayway.jsonpath.internal.JsonContext;
import com.vovk.jsonprocessingsystem.dao.impl.ArangoDAO;
import com.vovk.jsonprocessingsystem.model.JsonDocument;
import com.vovk.jsonprocessingsystem.services.DocumentService;
import com.vovk.jsonprocessingsystem.services.ProcessingService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Dmytro Vovk
 */

@Service
public class ProcessingServiceImpl implements ProcessingService {

    private ArangoDAO arangoDAO;
    private DocumentService documentService;

    public ProcessingServiceImpl(ArangoDAO arangoDAO, DocumentService documentService) {
        this.arangoDAO = arangoDAO;
        this.documentService = documentService;
    }

    @Override
    public String getValue(String jsonFileId, String path) {
        JsonDocument existingDocument = documentService.get(jsonFileId);
        String result = JsonPath.parse(existingDocument.getBody().toString())
                .read(JsonPath.compile(path));
        return result;
    }

    @Override
    public JSONObject get(String jsonFileId, String path) {
        JsonDocument existingDocument = documentService.get(jsonFileId);
        Map resultAsMap = JsonPath.parse(existingDocument.getBody().toString())
                .read(JsonPath.compile(path));
        JSONObject result = new JSONObject(resultAsMap);
        return result;
    }

    @Override
    public void merge(String jsonFileId, JSONObject jsonData) {
        JsonDocument existingDocument = documentService.get(jsonFileId);
        existingDocument.getBody().putAll(jsonData);
        arangoDAO.update(existingDocument);
    }

    @Override
    public void delete(String jsonFileId, String jsonPath) {
        JsonDocument existingDocument = documentService.get(jsonFileId);
        JsonContext documentContext = (JsonContext) JsonPath.parse(existingDocument.getBody().toString())
                .delete(jsonPath);
        JSONObject result = new JSONObject((Map) documentContext.json());
        existingDocument.setBody(result);
        arangoDAO.update(existingDocument);
    }

    @Override
    public void add(String jsonFileId, String pathToPlaceIn, JSONObject jsonData, String key) {
        JsonDocument existingDocument = documentService.get(jsonFileId);
        JsonContext documentContext = (JsonContext) JsonPath.parse(existingDocument.getBody().toString())
                .put(JsonPath.compile(pathToPlaceIn), key, jsonData);
        JSONObject result = new JSONObject((Map) documentContext.json());
        existingDocument.setBody(result);
        arangoDAO.update(existingDocument);
    }

    @Override
    public void replace(String jsonFileId, String pathToPlaceIn, JSONObject jsonData) {
        JsonDocument existingDocument = documentService.get(jsonFileId);
        JsonContext documentContext = (JsonContext) JsonPath.parse(existingDocument.getBody().toString())
                .set(JsonPath.compile(pathToPlaceIn), jsonData);
        JSONObject result = new JSONObject((Map) documentContext.json());
        existingDocument.setBody(result);
        arangoDAO.update(existingDocument);
    }
}
