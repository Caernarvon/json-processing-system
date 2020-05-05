package com.vovk.jsonprocessingsystem.services.impl;

import com.vovk.jsonprocessingsystem.dao.impl.ArangoDAO;
import com.vovk.jsonprocessingsystem.model.JsonDocument;
import com.vovk.jsonprocessingsystem.model.exceptions.BadFileFormatException;
import com.vovk.jsonprocessingsystem.model.exceptions.InvalidContentException;
import com.vovk.jsonprocessingsystem.model.exceptions.NoSuchEntityException;
import com.vovk.jsonprocessingsystem.services.ProcessingService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.vovk.jsonprocessingsystem.constants.CommonConstants.JSON_EXTENSION;
import static com.vovk.jsonprocessingsystem.constants.ErrorMessages.*;

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
    public JSONObject get(String jsonFileId) {
        return arangoDAO.findById(jsonFileId).getBody();
    }

    @Override
    public String save(MultipartFile multipartFile) {

        if (StringUtils.isEmpty(multipartFile.getOriginalFilename())
                || !multipartFile.getOriginalFilename().contains(JSON_EXTENSION)) {
            throw new BadFileFormatException(BAD_FILE_FORMAT);
        }
        try (InputStream inputStream = new BufferedInputStream(multipartFile.getInputStream());
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(inputStreamReader);
            JsonDocument document = new JsonDocument(jsonObject);
            return arangoDAO.create(document);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new InvalidContentException(INVALID_FILE_CONTENT);
        }
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
    public void delete(String jsonFileId) {
        try {
            arangoDAO.delete(jsonFileId);
        } catch (DataAccessException e) {
            throw new NoSuchEntityException(ENTITY_NOT_FOUND);
        }
    }
}
