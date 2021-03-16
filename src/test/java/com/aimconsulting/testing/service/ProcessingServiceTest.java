package com.aimconsulting.testing.service;

import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository_interface.ResultWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ProcessingServiceTest {

    private final Parser parser = Mockito.mock(Parser.class);
    private final ResultWriter writer = Mockito.mock(ResultWriter.class);
    private final ProcessingService service = new ProcessingService(parser, writer);

    @Test
    void testParse() {
        ContentDtoRequest request = new ContentDtoRequest("id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;");

        List<Result> parserAnswer = new ArrayList<>();
        parserAnswer.add(new Result("id", "0;1;2;"));
        parserAnswer.add(new Result("version", "1;2;"));
        parserAnswer.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        ResultDtoResponse answer = new ResultDtoResponse(parserAnswer);
        when(parser.parse(anyString())).thenReturn(parserAnswer);

        assertEquals(answer, service.parse(request));
    }

    @Test
    void testGetResult() {
        String name = "id";
        List<Result> parserAnswer = new ArrayList<>();
        parserAnswer.add(new Result("id", "0;1;2;"));
        parserAnswer.add(new Result("id", "3;6;7"));

        when(writer.getResult(anyString())).thenReturn(parserAnswer);

        assertEquals(new Result("id", "0;1;2;3;6;7"), service.getResult(name));
    }

    @Test
    void testDeleteResult() {
        assertDoesNotThrow(() -> service.deleteResult("id"));
    }
}