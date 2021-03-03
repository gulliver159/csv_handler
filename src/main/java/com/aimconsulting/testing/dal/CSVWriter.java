package com.aimconsulting.testing.dal;

import com.aimconsulting.testing.Main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Set;

public class CSVWriter implements Runnable {
    private final String filename;
    private final Set<String> fileContent;

    public CSVWriter(String filename, Set<String> fileContent) {
        this.filename = filename;
        this.fileContent = fileContent;
    }

    public void run() {
        Path path = Paths.get(filename);
        try {
            Files.write(path, (String.join(Main.SEPARATOR, fileContent) + Main.SEPARATOR).getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
