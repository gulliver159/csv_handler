package com.aimconsulting.testing.service;

import com.aimconsulting.testing.model.Result;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Parser {

    public static final String SEPARATOR = ";";

    public List<Result> parse(String content) {
        Map<String, Set<String>> data = new LinkedHashMap<>();
        Map<Integer, String> indexKeysData = new HashMap<>();

        String[] keysAndValues = content.split("\n", 2);
        if (keysAndValues.length == 0) {
            return createAnswer(data);
        }

        String[] keys = keysAndValues[0].split(SEPARATOR);
        int index = 0;
        for (String key : keys) {
            if (!data.containsKey(key)) {
                data.put(key, new LinkedHashSet<>());
            }
            indexKeysData.put(index++, key);
        }

        if (keysAndValues.length == 1) {
            return createAnswer(data);
        }
        index = 0;
        String[] values = keysAndValues[1].replace("\n", "").split(SEPARATOR);
        for (String value : values) {
            Set<String> valuesFromData = data.get(indexKeysData.get(index++ % 3));
            valuesFromData.add(value);
        }

        return createAnswer(data);
    }

    private List<Result> createAnswer(Map<String, Set<String>> data) {
        List<Result> resultList = new ArrayList<>();
        for (String name : data.keySet()) {
            String responseContent = String.join(Parser.SEPARATOR, data.get(name)) + Parser.SEPARATOR;
            resultList.add(new Result(name, responseContent));
        }
        return resultList;
    }
}
