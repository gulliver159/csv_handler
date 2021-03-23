package com.aimconsulting.testing.controller;

import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.CreateByUserDtoRequest;
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

    @PostMapping(value = "/csv", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDtoResponse handleCSV(@RequestBody ContentDtoRequest request) {
        return service.parse(request);
    }

    @GetMapping(value = "/csv/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result getResult(@PathVariable("name") String name) {
        return service.getResult(name);
    }

    @DeleteMapping(value = "/csv/{name}")
    public void deleteResult(@PathVariable("name") String name) {
        service.deleteResult(name);
    }

    @PostMapping(value = "/csv/clear")
    public void clearStateServer() {
        service.deleteAll();
    }

    @PostMapping(value = "/csv/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultDtoResponse createByUser(@RequestBody CreateByUserDtoRequest request) {
        return service.createByUser(request);
    }

    @GetMapping(value = "/csv/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultDtoResponse getResultByUser(@PathVariable("username") String username) {
        return service.getResultsByUser(username);
    }
}
