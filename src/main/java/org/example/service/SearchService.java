
package org.example.service;

import org.example.util.Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SearchService {
    private static long initTime;

    public Trie initialize(String dataFilePath, int indexedColumnId) throws IOException {
        long startTime = System.currentTimeMillis();
        Trie trie = new Trie();

        try (BufferedReader br = new BufferedReader(new FileReader(dataFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.replace("\"", "").split(",");
                String key = values[indexedColumnId];
                short value = Short.parseShort(values[0]);
                trie.insert(key, Collections.singletonList(value));
            }
        }

        initTime = System.currentTimeMillis() - startTime;
        return trie;
    }

    public long getInitTime() {
        return initTime;
    }
}