package com.aimconsulting.testing.service;

import com.aimconsulting.testing.dao.Writer;
import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import com.aimconsulting.testing.model.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessingService {

    private final Parser parser;
    private final Writer writer;

    public ProcessingService(Parser parser, Writer writer) {
        this.parser = parser;
        this.writer = writer;
    }

    public ResultDtoResponse parse(ContentDtoRequest contentDtoRequest) {
        String requestContent = contentDtoRequest.getContent();
        List<Result> resultList =  parser.parse(requestContent);

        for (Result result : resultList) {
            writer.createResult(result);
        }

        return new ResultDtoResponse(resultList);
    }

}
