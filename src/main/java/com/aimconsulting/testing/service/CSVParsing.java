package com.aimconsulting.testing.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class CSVParsing  {

    public static final String SEPARATOR = ";";

    public HashMap<String, Set<String>> parse(String content) {
        HashMap<String, Set<String>> data = new HashMap<>();
        HashMap<Integer, String> indexKeysData = new HashMap<>();

        String[] keysAndValues = content.split("\n", 2);
        if (keysAndValues.length == 0) {
            return data;
        }

        String[] keys = keysAndValues[0].split(SEPARATOR);
        int index = 0;
        for (String key : keys) {
            if (!data.containsKey(key)) {
                data.put(key, new HashSet<>());
            }
            indexKeysData.put(index++, key);
        }

        if (keysAndValues.length == 1) {
            return data;
        }
        index = 0;
        String[] values = keysAndValues[1].replace("\n", "").split(SEPARATOR);
        for (String value : values) {
            Set<String> valuesFromData = data.get(indexKeysData.get(index++ % 3));
            valuesFromData.add(value);
        }
        return data;
    }
}
