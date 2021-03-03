package com.aimconsulting.testing.controller;

import com.aimconsulting.testing.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csv_handling")
public class CSVController {
    private final CSVService service;

    @Autowired
    public CSVController(CSVService service) {
        this.service = service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void handleCSV() {
        // вызов метода service
    }
}
