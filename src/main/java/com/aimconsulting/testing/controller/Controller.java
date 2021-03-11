package com.aimconsulting.testing.controller;

import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.service.ProcessingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final ProcessingService service;

    public Controller(ProcessingService service) {
        this.service = service;
    }

    @PostMapping(value = "/csv/handling", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDtoResponse handleCSV(@RequestBody ContentDtoRequest request) {
        return service.parse(request);
    }

    @GetMapping(value = "/csv/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result getResult(@PathVariable("name") String name) {
        return service.getResult(name);
    }
}
