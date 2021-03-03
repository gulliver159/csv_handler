package com.aimconsulting.testing.controller;

import com.aimconsulting.testing.dto.FileBodyDtoRequest;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import com.aimconsulting.testing.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/csv_handling")
public class CSVController {
    private final CSVService service;

    @Autowired
    public CSVController(CSVService service) {
        this.service = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDtoResponse handleCSV(@RequestBody FileBodyDtoRequest request) {
        return service.parse(request);
    }
}
