package com.vovk.jsonprocessingsystem.services;

import com.vovk.jsonprocessingsystem.model.JsonDocument;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dmytro Vovk
 */

public interface DocumentService {

    JsonDocument get (String jsonFileId);

    String save (MultipartFile multipartFile);

    String save (JSONObject jsonFile);

    void delete(String jsonFileId);
}
