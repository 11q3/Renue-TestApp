package org.example.service;

import org.example.data.SearchResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SearchService {
    private static long initTime;

    public Map<String, List<Integer>> initialize(String dataFilePath, int indexedColumnId) throws IOException {
        long startTime = System.currentTimeMillis();
        Map<String, List<Integer>> data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dataFilePath))) {
            String line;
            while ((line = br.readLine())!= null) {
                String[] values = line.replace("\"", "").split(",");
                String key = values[indexedColumnId];
                int value = Integer.parseInt(values[0]);

                if (!data.containsKey(key)) {
                    data.put(key, new ArrayList<>());
                }
                data.get(key).add(value);
            }
        }

        initTime = System.currentTimeMillis() - startTime;
        return data;
    }

    public long getInitTime() {
        return initTime;
    }
}