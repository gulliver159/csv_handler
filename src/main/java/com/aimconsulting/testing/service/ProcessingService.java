package com.aimconsulting.testing.service;

import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.repository.ResultWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessingService {

    private final Parser parser;
    private final ResultWriter resultWriter;

    public ProcessingService(Parser parser, ResultWriter resultRepositoryMyBatis) {
        this.parser = parser;
        this.resultWriter = resultRepositoryMyBatis;
    }

    public ResultDtoResponse parse(ContentDtoRequest request) {
        String content = request.getContent();
        List<Result> resultList =  parser.parse(content);

        resultWriter.createResults(resultList);

        return new ResultDtoResponse(resultList);
    }

    public Result getResult(String name) {
        List<Result> results = resultWriter.getResult(name);

        StringBuilder contentBuilder = new StringBuilder();
        for (Result result : results) {
            contentBuilder.append(result.getContent());
        }
        return new Result(name, contentBuilder.toString());
    }

    public void deleteResult(String name) {
        resultWriter.deleteResult(name);
    }

    public void deleteAll() {
        resultWriter.deleteAll();
    }
}
