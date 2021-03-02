package com.aimconsulting.testing;

import java.util.HashMap;
import java.util.Set;

public class CSVHandler implements Runnable {
    private final String filename;
    private final CSVReader reader = new CSVReader();
    private final CSVParsing parsing = new CSVParsing();

    public CSVHandler(String filename) {
        this.filename = filename;
    }

    public void run() {
        String fileBody = reader.read(filename);
        HashMap<String, Set<String>> data = parsing.parse(fileBody);
        for (String filename : data.keySet()) {
            new Thread(new CSVWriter(filename, data.get(filename))).start();
        }
    }
}
