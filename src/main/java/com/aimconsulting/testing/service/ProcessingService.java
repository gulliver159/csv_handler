package com.aimconsulting.testing.service;

import com.aimconsulting.testing.dao.Writer;
import com.aimconsulting.testing.dto.ContentDtoRequest;
import com.aimconsulting.testing.dto.ResultInfoDtoResponse;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
public class ProcessingService {

    @Autowired
    private final Parser parser;
    @Autowired
    private final Writer writer;

    public ProcessingService(Parser parser, Writer writer) {
        this.parser = parser;
        this.writer = writer;
    }

    public ResultDtoResponse parse(ContentDtoRequest contentDtoRequest) {
        String requestContent = contentDtoRequest.getContent();
        List<ResultInfoDtoResponse> resultList = new ArrayList<>();

        HashMap<String, Set<String>> data =  parser.parse(requestContent);
        // вызов метода writer

        for (String name : data.keySet()) {
            String responseContent = String.join(Parser.SEPARATOR, data.get(name)) + Parser.SEPARATOR;
            resultList.add(new ResultInfoDtoResponse(name, responseContent));
        }
        return new ResultDtoResponse(resultList);
    }

}
