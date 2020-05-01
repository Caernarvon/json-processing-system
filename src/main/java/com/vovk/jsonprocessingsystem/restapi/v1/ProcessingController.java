package com.vovk.jsonprocessingsystem.restapi.v1;

import com.vovk.jsonprocessingsystem.services.ProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dmytro Vovk
 */

@Slf4j
@RestController
@RequestMapping("/v1/process")
public class ProcessingController {

    private ProcessingService processingService;

    @Autowired
    public ProcessingController(ProcessingService processingService) {
        this.processingService = processingService;
    }

    @PostMapping(value = "/upload/file")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity upload(@RequestParam("jsonFile") MultipartFile jsonFile) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/download/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity download(@PathVariable("uuid") String documentUUID) {
        return new ResponseEntity<>(processingService.get(documentUUID), HttpStatus.OK);
    }

    @PostMapping(value = "/upload/data")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity upload(@RequestBody JSONObject json) {
        String id = processingService.save(json);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{jsonId}")
    public ResponseEntity delete(@PathVariable String jsonId) {
        processingService.delete(jsonId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/insert/{jsonFileId}")
    public ResponseEntity insert (@PathVariable String jsonFileId, @RequestBody JSONObject jsonData) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
