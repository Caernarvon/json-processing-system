package com.vovk.jsonprocessingsystem.restapi.v1;

import com.vovk.jsonprocessingsystem.services.ProcessingService;
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
@RequestMapping("/v1/process")
public class ProcessingController {

    private ProcessingService processingService;

    @Autowired
    public ProcessingController(ProcessingService processingService) {
        this.processingService = processingService;
    }

    @PostMapping(value = "/file")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity upload(@RequestParam("jsonFile") MultipartFile jsonFile) {
        String id = processingService.save(jsonFile);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    //TODO not return file that longer than 10000 rows
    @GetMapping(value = "/{jsonFileId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity download(@PathVariable("jsonFileId") String jsonFileId) {
        return new ResponseEntity<>(processingService.get(jsonFileId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity upload(@RequestBody JSONObject jsonFile) {
        String id = processingService.save(jsonFile);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{jsonFileId}")
    public ResponseEntity delete(@PathVariable String jsonFileId) {
        processingService.delete(jsonFileId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{jsonFileId}")
    public ResponseEntity insert (@PathVariable String jsonFileId, @RequestBody JSONObject jsonData) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
