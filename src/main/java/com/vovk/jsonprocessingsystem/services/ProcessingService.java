package com.vovk.jsonprocessingsystem.services;

import org.json.simple.JSONObject;

/**
 * @author Dmytro Vovk
 */

public interface ProcessingService {

    String getValue(String jsonFileId, String path);

    JSONObject get (String jsonFileId, String path);

    void merge (String jsonFileId, JSONObject jsonData);

    void delete (String jsonFileId, String jsonPath);

    void add (String jsonFileId, String pathToPlaceIn, JSONObject jsonData, String key);

    void replace (String jsonFileId, String pathToPlaceIn, JSONObject jsonData);
}
