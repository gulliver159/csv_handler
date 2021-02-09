package com.aimconsulting.testing;

import java.util.HashMap;
import java.util.Set;

public class CSVWriter implements Runnable {
    private String filename;
    private Set<String> fileContent;

    public CSVWriter(String filename, Set<String> fileContent) {
        this.filename = filename;
        this.fileContent = fileContent;
    }

    public void run() {
    }
}
