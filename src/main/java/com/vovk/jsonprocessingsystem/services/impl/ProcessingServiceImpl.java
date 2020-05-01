package com.vovk.jsonprocessingsystem.services.impl;

import com.vovk.jsonprocessingsystem.dao.impl.ArangoDAO;
import com.vovk.jsonprocessingsystem.model.JsonDocument;
import com.vovk.jsonprocessingsystem.services.ProcessingService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author Dmytro Vovk
 */

@Service
public class ProcessingServiceImpl implements ProcessingService {


    private ArangoDAO arangoDAO;

    public ProcessingServiceImpl(ArangoDAO arangoDAO) {
        this.arangoDAO = arangoDAO;
    }

    @Override
    public JSONObject get(String uuid) {
        return arangoDAO.findById(uuid).getBody();
    }

    @Override
    public String save(MultipartFile multipartFile) {
        return null;
    }

    @Override
    public String save(JSONObject json) {
        JsonDocument document = new JsonDocument(json);
        return arangoDAO.create(document);
    }

    @Override
    public void update(String jsonFileId, String jsonData) {

    }

    @Override
    public void delete(String UUID) {
        arangoDAO.delete(UUID);
    }
}
