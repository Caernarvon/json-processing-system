package com.vovk.jsonprocessingsystem.services.impl;

import com.vovk.jsonprocessingsystem.services.ProcessingService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Dmytro Vovk
 */

@Service
public class ProcessingServiceImpl implements ProcessingService {


    @Override
    public void delete(String UUID) {
        if(UUID.equals("")) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        // throw custom ex if UUID isn't found
    }
}
