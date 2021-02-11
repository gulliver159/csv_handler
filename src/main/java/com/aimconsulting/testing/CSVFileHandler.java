package com.aimconsulting.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CSVFileHandler {
    static final String SEPARATOR = ";";

    public static void main(String[] args) {
        HashMap<String, Set<String>> data = new HashMap<>();

        List<Thread> threadsReaders = new ArrayList<>();

        for (String filename : args) {
            Thread thread = new Thread(new CSVReader(filename, data));
            threadsReaders.add(thread);
            thread.start();
        }

        for (Thread thread : threadsReaders) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (String filename : data.keySet()) {
            Thread thread = new Thread(new CSVWriter(filename, data.get(filename)));
            thread.start();
        }

    }
}
