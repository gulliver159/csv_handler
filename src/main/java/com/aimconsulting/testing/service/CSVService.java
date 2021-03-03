package com.aimconsulting.testing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class CSVService {

    private final CSVParsing parsing;

    @Autowired
    public CSVService(CSVParsing parsing) {
        this.parsing = parsing;
    }

    public HashMap<String, Set<String>> parse(String fileBody) {
        return parsing.parse(fileBody);
    }

}
