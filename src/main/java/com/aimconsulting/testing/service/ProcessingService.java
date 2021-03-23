package com.aimconsulting.testing.service;

import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.CreateByUserDtoRequest;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import com.aimconsulting.testing.model.Result;
import com.aimconsulting.testing.model.User;
import com.aimconsulting.testing.repository.ResultWriter;
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

    public ResultDtoResponse createByUser(CreateByUserDtoRequest request) {
        String content = request.getContent();
        List<Result> resultList =  parser.parse(content);
        User user = request.getUser();

        for (Result result : resultList) {
            result.setUser(user);
        }
        resultWriter.createResultsByUser(resultList);

        return new ResultDtoResponse(resultList);
    }

    public ResultDtoResponse getResultsByUser(String username) {
        List<Result> results = resultWriter.getResultsByUsername(username);
        for (Result result : results) {
            result.getUser().setResults(null);
        }
        return new ResultDtoResponse(results);
    }

    public void deleteResultsByUsername(String username) {
        resultWriter.deleteByUserName(username);
    }
}
