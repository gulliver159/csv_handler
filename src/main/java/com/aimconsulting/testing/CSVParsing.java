package com.aimconsulting.testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    }
}
