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
        List<Result> expectedAnswer = new ArrayList<>();
        expectedAnswer.add(new Result("id", "0;1;2;"));
        expectedAnswer.add(new Result("version", "1;2;"));
        expectedAnswer.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        List<Result> actualAnswer = parser.parse(content);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void testParseTwoDivisions() {
        String content = "name;sex;\n" +
                "ричард;м;\n" +
                "жорж;м;\n" +
                "мария;ж;\n" +
                "пьер;м;";
        List<Result> expectedAnswer = new ArrayList<>();
        expectedAnswer.add(new Result("name", "ричард;жорж;мария;пьер;"));
        expectedAnswer.add(new Result("sex", "м;ж;"));

        List<Result> actualAnswer = parser.parse(content);

        assertEquals(expectedAnswer, actualAnswer);
    }
}