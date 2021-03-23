package com.aimconsulting.testing.service;

import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.CreateByUserDtoRequest;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.model.User;
import com.aimconsulting.testing.repository.ResultWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProcessingServiceTest {

    private final Parser parser = Mockito.mock(Parser.class);
    private final ResultWriter writer = Mockito.mock(ResultWriter.class);
    private final ProcessingService service = new ProcessingService(parser, writer);

    @Test
    void testCreateResults() {
        ContentDtoRequest request = new ContentDtoRequest("id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;");

        List<Result> parserAnswer = new ArrayList<>();
        parserAnswer.add(new Result("id", "0;1;2;"));
        parserAnswer.add(new Result("version", "1;2;"));
        parserAnswer.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        ResultDtoResponse expectedAnswer = new ResultDtoResponse(parserAnswer);
        when(parser.parse(anyString())).thenReturn(parserAnswer);

        ResultDtoResponse actualAnswer = service.createResults(request);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void testGetResult() {
        String name = "id";
        List<Result> writerAnswer = new ArrayList<>();
        writerAnswer.add(new Result("id", "0;1;2;"));
        writerAnswer.add(new Result("id", "3;6;7"));

        when(writer.getResult(anyString())).thenReturn(writerAnswer);

        Result expectedAnswer = new Result("id", "0;1;2;3;6;7");
        Result actualAnswer = service.getResult(name);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void testDeleteResult() {
        service.deleteResult("id");
        verify(writer, times(1)).deleteResult("id");
    }

    @Test
    void testCreateResultsByUser() {
        User user = new User("Roma");
        CreateByUserDtoRequest request = new CreateByUserDtoRequest("id;version;path;\n" +
                "0;1;/hello/уточка;\n" +
                "1;2;/hello/лошадка;\n" +
                "2;2;/hello/собачка;", user);

        List<Result> parserAnswer = new ArrayList<>();
        parserAnswer.add(new Result("id", "0;1;2;"));
        parserAnswer.add(new Result("version", "1;2;"));
        parserAnswer.add(new Result("path", "/hello/уточка;/hello/лошадка;/hello/собачка;"));

        ResultDtoResponse expectedAnswer = new ResultDtoResponse(parserAnswer);
        when(parser.parse(anyString())).thenReturn(parserAnswer);

        ResultDtoResponse actualAnswer = service.createResultsByUser(request);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void testGetResultsByUsername() {
        String name = "id";

        List<Result> writerAnswer = new ArrayList<>();
        writerAnswer.add(new Result("id", "0;1;2;"));
        writerAnswer.add(new Result("version", "3;6;7"));

        User user = new User("Roma");
        for (Result result : writerAnswer) {
            result.setUser(user);
        }

        when(writer.getResultsByUsername(anyString())).thenReturn(writerAnswer);

        ResultDtoResponse expectedAnswer = new ResultDtoResponse(writerAnswer);
        ResultDtoResponse actualAnswer = service.getResultsByUser(name);

        assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void testDeleteResultsByUsername() {
        service.deleteResultsByUsername("Roma");
        verify(writer, times(1)).deleteResultsByUsername("Roma");
    }
}