package com.aimconsulting.testing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CSVParsing implements Runnable {
    private final String fileBody;
    private final HashMap<String, Set<String>> data;

    public CSVParsing(String fileBody, HashMap<String, Set<String>> data) {
        this.fileBody = fileBody;
        this.data = data;
    }

    public void run() {
        HashMap<Integer, String> indexKeysData = new HashMap<>();

        String[] keysAndValues = fileBody.split("\n", 2);
        String[] keys = keysAndValues[0].split(CSVFileHandler.SEPARATOR);
        int index = 0;
        for (String key : keys) {
            synchronized (data) {
                if (!data.containsKey(key)) {
                    data.put(key, new HashSet<>());
                }
            }
            indexKeysData.put(index++, key);
        }

        index = 0;
        String[] values = keysAndValues[1].replace("\n", "").split(CSVFileHandler.SEPARATOR);
        for (String value : values) {
            Set<String> valuesFromData = data.get(indexKeysData.get(index++ % 3));
            valuesFromData.add(value);
        }
    }
}
