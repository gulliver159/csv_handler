package com.aimconsulting.testing.service;

import com.aimconsulting.testing.Main;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Component
public class CSVParsing  {

    public HashMap<String, Set<String>> parse(String fileBody) {
        HashMap<String, Set<String>> data = new HashMap<>();
        HashMap<Integer, String> indexKeysData = new HashMap<>();

        String[] keysAndValues = fileBody.split("\n", 2);
        if (keysAndValues.length == 0) {
            return data;
        }

        String[] keys = keysAndValues[0].split(Main.SEPARATOR);
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
        String[] values = keysAndValues[1].replace("\n", "").split(Main.SEPARATOR);
        for (String value : values) {
            Set<String> valuesFromData = data.get(indexKeysData.get(index++ % 3));
            valuesFromData.add(value);
        }
        return data;
    }
}
