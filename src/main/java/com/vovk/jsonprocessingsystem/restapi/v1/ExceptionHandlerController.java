package com.vovk.jsonprocessingsystem.restapi.v1;

import com.vovk.jsonprocessingsystem.model.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Dmytro Vovk
 */

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BadFileFormatException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    protected  ResponseEntity<ResponseMessage> handleBFFE(BadFileFormatException ex) {
        return new ResponseEntity<>(new ResponseMessage(ex.getMessage()), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(InvalidContentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected  ResponseEntity<ResponseMessage> handleICE(InvalidContentException ex) {
        return new ResponseEntity<>(new ResponseMessage(ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected  ResponseEntity<ResponseMessage> handleNSEE(EntityNotFoundException ex) {
        return new ResponseEntity<>(new ResponseMessage(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
