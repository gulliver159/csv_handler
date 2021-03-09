package com.aimconsulting.testing.service;

import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.NameDtoRequest;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository_interface.ResultWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProcessingServiceTest {

    @MockBean
    private Parser parser;

    @MockBean
    ResultWriter writer;

    @Autowired
    private ProcessingService service;

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
        NameDtoRequest request = new NameDtoRequest("id");
        Result answer = new Result("id", "0;1;2;");

        when(writer.getResult(anyString())).thenReturn(answer);

        assertEquals(answer, service.getResult(request));
    }
}