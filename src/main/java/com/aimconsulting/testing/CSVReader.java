package com.aimconsulting.testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader implements Runnable {
    private final String filename;
    private final HashMap<String, String> files;

    public CSVReader(String filename, HashMap<String, String> files) {
        this.filename = filename;
        this.files = files;
    }

    public void run() {
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            files.put(filename, lines.collect(Collectors.joining("\n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
