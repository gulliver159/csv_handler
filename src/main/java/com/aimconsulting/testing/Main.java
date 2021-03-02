package com.aimconsulting.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Main {
    static final String SEPARATOR = ";";

    public static void main(String[] args) {
        for (String filename : args) {
            new Thread(new CSVHandler(filename)).start();
        }
    }
}
