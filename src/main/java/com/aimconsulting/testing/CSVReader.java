package com.aimconsulting.testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CSVReader implements Runnable {
    private final String filename;
    private final HashMap<String, Set<String>> data;

    public CSVReader(String filename, HashMap<String, Set<String>> data) {
        this.filename = filename;
        this.data = data;
    }

    public void run() {
        HashMap<Integer, String> indexKeysData = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String st;
            if ((st = br.readLine()) != null) {
                String[] keys = st.trim().split(CSVFileHandler.SEPARATOR);
                int index = 0;
                for (String key : keys) {
                    synchronized (data) {
                        if (!data.containsKey(key)) {
                            data.put(key, new HashSet<>());
                        }
                    }
                    indexKeysData.put(index++, key);
                }
            }

            while ((st = br.readLine()) != null) {
                String[] values = st.trim().split(CSVFileHandler.SEPARATOR);
                int index = 0;
                for (String value : values) {
                    Set<String> valuesFromData = data.get(indexKeysData.get(index++));
                    valuesFromData.add(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
