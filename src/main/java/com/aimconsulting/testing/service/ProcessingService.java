package com.aimconsulting.testing.service;

import com.aimconsulting.testing.dto.NameDtoRequest;
import com.aimconsulting.testing.repository_interface.ResultWriter;
import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import com.aimconsulting.testing.model.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessingService {

    private final Parser parser;
    private final ResultWriter resultWriter;

    public ProcessingService(Parser parser, ResultWriter resultWriter) {
        this.parser = parser;
        this.resultWriter = resultWriter;
    }

    public ResultDtoResponse parse(ContentDtoRequest request) {
        String content = request.getContent();
        List<Result> resultList =  parser.parse(content);

        resultWriter.createResults(resultList);

        return new ResultDtoResponse(resultList);
    }

    public Result getResult(NameDtoRequest request) {
        return resultWriter.getResult(request.getName());
    }

}
