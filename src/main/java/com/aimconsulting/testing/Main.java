package com.aimconsulting.testing;

public class Main {
    public static final String SEPARATOR = ";";

    public static void main(String[] args) {
        for (String filename : args) {
            new Thread(new CSVHandler(filename)).start();
        }
    }
}
