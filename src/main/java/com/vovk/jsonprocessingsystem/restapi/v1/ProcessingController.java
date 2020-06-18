package com.vovk.jsonprocessingsystem.restapi.v1;

import com.vovk.jsonprocessingsystem.services.ProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dmytro Vovk
 */

@Slf4j
@RestController
@RequestMapping("/v1/process")
public class ProcessingController {

    private ProcessingService processingService;

    public ProcessingController(ProcessingService processingService) {
        this.processingService = processingService;
    }

    @GetMapping(value = "/{jsonFileId}")
    public ResponseEntity get (@PathVariable String jsonFileId, @RequestParam String path) {
        return new ResponseEntity<>(processingService.get(jsonFileId, path), HttpStatus.OK);
    }

    @PatchMapping(value = "/merge/{jsonFileId}")
    public ResponseEntity merge (@PathVariable String jsonFileId, @RequestBody JSONObject jsonFile) {
        processingService.merge(jsonFileId, jsonFile);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/insert/{jsonFileId}")
    public ResponseEntity insert (@PathVariable String jsonFileId, @RequestParam String path,
                                       @RequestParam String key, @RequestBody JSONObject jsonData) {
        processingService.add(jsonFileId, path, jsonData, key);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/replace/{jsonFileId}")
    public ResponseEntity replace (@PathVariable String jsonFileId, @RequestParam String path,
                                        @RequestBody JSONObject jsonData) {
        processingService.replace(jsonFileId, path, jsonData);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{jsonFileId}")
    public ResponseEntity delete (@PathVariable String jsonFileId, @RequestParam String path) {
        processingService.delete(jsonFileId, path);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //TODO ADD GET
}
