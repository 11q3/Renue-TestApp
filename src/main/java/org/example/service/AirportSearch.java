package org.example.service;


import org.example.util.Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AirportSearch {
    private Trie trie;
    private int indexedColumnId;

    private static long initTime;

    public Trie initialize(String dataFilePath, int indexedColumnId) throws IOException {
        long startTime = System.currentTimeMillis();
        trie = new Trie();
        this.indexedColumnId = indexedColumnId;

        try (BufferedReader br = new BufferedReader(new FileReader(dataFilePath))){
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = List.of(line.replace("\"", "").split(","));
                String key = values.get(indexedColumnId-1);
                int value = Integer.parseInt(values.get(0));
                trie.insert(key, value);
            }
            initTime = System.currentTimeMillis() - startTime;
            return trie;
        } //TODO do something with try catch
    }

    public long getInitTime() {
        return initTime;
    }
}