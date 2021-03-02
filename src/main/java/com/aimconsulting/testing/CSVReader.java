package com.aimconsulting.testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader {

    public String read(String filename) {
        String fileBody = null;
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            fileBody = lines.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileBody;
    }
}
