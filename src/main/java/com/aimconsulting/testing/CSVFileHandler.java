package com.aimconsulting.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CSVFileHandler {
    static final String SEPARATOR = ";";

    public static void main(String[] args) {
        HashMap<String, Set<String>> data = new HashMap<>();
        HashMap<String, String> files = new HashMap<>();

        List<Thread> threadsReaders = new ArrayList<>();
        List<Thread> threadsParsing = new ArrayList<>();

        for (String filename : args) {
            Thread thread = new Thread(new CSVReader(filename, files));
            threadsReaders.add(thread);
            thread.start();
        }

        waitThreads(threadsReaders);

        for (String fileBody : files.values()) {
            Thread thread = new Thread(new CSVParsing(fileBody, data));
            threadsParsing.add(thread);
            thread.start();
        }

        waitThreads(threadsParsing);

        for (String filename : data.keySet()) {
            Thread thread = new Thread(new CSVWriter(filename, data.get(filename)));
            thread.start();
        }

    }

    private static void waitThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
