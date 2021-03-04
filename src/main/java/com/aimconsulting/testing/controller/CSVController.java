package com.aimconsulting.testing.controller;

import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import com.aimconsulting.testing.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CSVController {

    @Autowired
    private final CSVService service;

    public CSVController(CSVService service) {
        this.service = service;
    }

    @PostMapping(value = "/csv_handling", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDtoResponse handleCSV(@RequestBody ContentDtoRequest request) {
        return service.parse(request);
    }
}
