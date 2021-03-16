package com.aimconsulting.testing.service;

import com.aimconsulting.testing.model.Result;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void testParseThreeDivisions() {
        String content = "id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;";
        List<Result> answer = new ArrayList<>();
        answer.add(new Result("id", "0;1;2;"));
        answer.add(new Result("version", "1;2;"));
        answer.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        assertEquals(answer, parser.parse(content));
    }

    @Test
    void testParseTwoDivisions() {
        String content = "name;sex;\n" +
                "ричард;м;\n" +
                "жорж;м;\n" +
                "мария;ж;\n" +
                "пьер;м;";
        List<Result> answer = new ArrayList<>();
        answer.add(new Result("name", "ричард;жорж;мария;пьер;"));
        answer.add(new Result("sex", "м;ж;"));

        assertEquals(answer, parser.parse(content));
    }
}