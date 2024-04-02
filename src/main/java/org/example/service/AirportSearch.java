package org.example.service;


import org.example.util.Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AirportSearch {
    private static long initTime;

    public Trie initialize(String dataFilePath, int indexedColumnId) throws IOException {
        long startTime = System.currentTimeMillis();
        Trie trie = new Trie();

        BufferedReader br = new BufferedReader(new FileReader(dataFilePath));
        String line;

        while ((line = br.readLine()) != null) {
            List<String> values = List.of(line.replace("\"", "").split(","));
            String key = values.get(indexedColumnId-1);
            int value = Integer.parseInt(values.get(0));
            trie.insert(key, value);
        }
        initTime = System.currentTimeMillis() - startTime;
        return trie;
    }

    public long getInitTime() {
        return initTime;
    }
}