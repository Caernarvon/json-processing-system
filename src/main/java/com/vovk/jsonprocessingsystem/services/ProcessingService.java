package com.vovk.jsonprocessingsystem.services;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dmytro Vovk
 */

public interface ProcessingService {

    JSONObject get (String uuid);

    String save (MultipartFile multipartFile);

    String save (JSONObject json);

    void update (String jsonFileId, String jsonData);

    void delete(String UUID);
}
