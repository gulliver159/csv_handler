package com.aimconsulting.testing.service;

import com.aimconsulting.testing.model.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ParserTest {

    @Autowired
    private Parser parser;

    @Test
    void testParse1() {
        String content = "id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;";
        Set<Result> answer = new HashSet<>();
        answer.add(new Result("id", "0;1;2;"));
        answer.add(new Result("version", "1;2;"));
        answer.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        assertEquals(answer, new HashSet<>(parser.parse(content)));
    }

    @Test
    void testParse2() {
        String content = "id;name;sex;\n" +
                "0;ричард;м;\n" +
                "1;жорж;м;\n" +
                "2;мария;ж;\n" +
                "3;пьер;м;";
        Set<Result> answer = new HashSet<>();
        answer.add(new Result("id", "0;1;2;3;"));
        answer.add(new Result("name", "ричард;жорж;мария;пьер;"));
        answer.add(new Result("sex", "м;ж;"));

        assertEquals(answer, new HashSet<>(parser.parse(content)));
    }
}