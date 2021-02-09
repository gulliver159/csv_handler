package com.aimconsulting.testing;

import java.util.HashMap;
import java.util.Set;

public class CSVReader implements Runnable {
    private String filename;
    private HashMap<String, Set<String>> data;

    public CSVReader(String filename, HashMap<String, Set<String>> data) {
        this.filename = filename;
        this.data = data;
    }

    public void run() {
    }
}
