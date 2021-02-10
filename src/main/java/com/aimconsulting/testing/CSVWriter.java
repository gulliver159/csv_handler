package com.aimconsulting.testing;

import java.io.*;
import java.util.Set;

public class CSVWriter implements Runnable {
    private final String filename;
    private final Set<String> fileContent;

    public CSVWriter(String filename, Set<String> fileContent) {
        this.filename = filename;
        this.fileContent = fileContent;
    }

    public void run() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(String.join(CSVFileHandler.SEPARATOR, fileContent) + CSVFileHandler.SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
