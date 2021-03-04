package com.aimconsulting.testing.dao;

import com.aimconsulting.testing.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class Writer {
    @Autowired
    private final JdbcTemplate template;

    public Writer(JdbcTemplate template) {
        this.template = template;
    }

    public void createResult(Result result) {

    }
}
