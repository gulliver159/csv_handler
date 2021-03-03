package com.aimconsulting.testing.service;

import com.aimconsulting.testing.Main;
import com.aimconsulting.testing.dto.FileBodyDtoRequest;
import com.aimconsulting.testing.dto.FileInfoDtoResponse;
import com.aimconsulting.testing.dto.ResultDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class CSVService {

    private final CSVParsing parsing;

    @Autowired
    public CSVService(CSVParsing parsing) {
        this.parsing = parsing;
    }

    public ResultDtoResponse parse(FileBodyDtoRequest fileBodyDtoRequest) {
        String fileBody = fileBodyDtoRequest.getFileBody();
        List<FileInfoDtoResponse> resultList = new ArrayList<>();

        HashMap<String, Set<String>> data =  parsing.parse(fileBody);

        for (String fileName : data.keySet()) {
            String content = String.join(Main.SEPARATOR, data.get(fileName)) + Main.SEPARATOR;
            resultList.add(new FileInfoDtoResponse(fileName, content));
        }
        return new ResultDtoResponse(resultList);
    }

}
