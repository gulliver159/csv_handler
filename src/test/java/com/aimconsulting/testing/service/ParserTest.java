package com.aimconsulting.testing.service;

import com.aimconsulting.testing.model.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void testParse1() {
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
    void testParse2() {
        String content = "id;name;sex;\n" +
                "0;ричард;м;\n" +
                "1;жорж;м;\n" +
                "2;мария;ж;\n" +
                "3;пьер;м;";
        List<Result> answer = new ArrayList<>();
        answer.add(new Result("id", "0;1;2;3;"));
        answer.add(new Result("name", "ричард;жорж;мария;пьер;"));
        answer.add(new Result("sex", "м;ж;"));

        assertEquals(answer, parser.parse(content));
    }
}