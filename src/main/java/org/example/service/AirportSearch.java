package org.example.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportSearch {
    private Trie trie;
    private int indexedColumnId;

    public void initialize(String dataFilePath, int indexedColumnId) throws IOException {
        Trie trie = new Trie();

        try (BufferedReader br = new BufferedReader(new FileReader(dataFilePath))){
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = List.of(line.replace("\"", "").split(","));
                String key = values.get(indexedColumnId-1);
                int value = Integer.parseInt(values.get(0));
                trie.insert(key, value);
            }
        }
    }
}
