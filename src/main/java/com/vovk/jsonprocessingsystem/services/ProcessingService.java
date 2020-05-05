package com.vovk.jsonprocessingsystem.services;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dmytro Vovk
 */

public interface ProcessingService {

    JSONObject get (String jsonFileId);

    String save (MultipartFile multipartFile);

    String save (JSONObject jsonFile);

    void update (String jsonFileId, String jsonData);

    void delete(String jsonFileId);
}
