package org.example.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SearchService {
    private static long initTime;

    public Map<String, List<Integer>> initialize(String dataFilePath, int indexedColumnId) throws IOException {
        long startTime = System.nanoTime();
        Map<String, List<Integer>> data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dataFilePath))) {
            String line;
            while ((line = br.readLine())!= null) {
                String[] values = line.replace("\"", "").split(",");
                if (indexedColumnId < 1 || indexedColumnId >= values.length) {
                    System.err.println("Indexed column ID is out of bounds.");
                    System.exit(1);
                }
                String key = values[indexedColumnId];
                int value = Integer.parseInt(values[0]);

                if (!data.containsKey(key)) {
                    data.put(key, new ArrayList<>());
                }
                data.get(key).add(value);
            }
        }

        initTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        return data;
    }

    public long getInitTime() {
        return initTime;
    }
}