package com.vovk.jsonprocessingsystem.restapi.v1;

import com.vovk.jsonprocessingsystem.services.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dmytro Vovk
 */

@Slf4j
@RestController
@RequestMapping("/v1/documents")
public class DocumentController {

    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(value = "/file")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity upload(@RequestParam("jsonFile") MultipartFile jsonFile) {
        String id = documentService.save(jsonFile);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{jsonFileId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity download(@PathVariable("jsonFileId") String jsonFileId) {
        return new ResponseEntity<>(documentService.get(jsonFileId).getBody(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity upload(@RequestBody JSONObject jsonFile) {
        String id = documentService.save(jsonFile);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{jsonFileId}")
    public ResponseEntity delete(@PathVariable String jsonFileId) {
        documentService.delete(jsonFileId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
