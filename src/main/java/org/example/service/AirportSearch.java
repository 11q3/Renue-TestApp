package org.example.service;


import org.example.model.SearchResult;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportSearch {
    private Trie trie;
    private int indexedColumnId;

    public Trie initialize(String dataFilePath, int indexedColumnId) throws IOException {
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
            return trie;
        }
    }
}