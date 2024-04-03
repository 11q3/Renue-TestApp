package org.example.service;


import org.example.util.Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchService {
    private static long initTime;

    public Trie initialize(String dataFilePath, int indexedColumnId) throws IOException {
        long startTime = System.currentTimeMillis();
        Trie trie = new Trie();

        try (BufferedReader br = new BufferedReader(new FileReader(dataFilePath))) {
            StringBuilder values = new StringBuilder();
            List<String> valuesList;
            String line;
            while ((line = br.readLine()) != null) {
                values.setLength(0); // reset the StringBuilder
                for (String s : line.replace("\"", "").split(",")) {
                    values.append(s).append(" ");
                }
                valuesList = List.of(values.toString().trim().split(" "));
                String key = valuesList.get(indexedColumnId-1);
                int value = Integer.parseInt(valuesList.get(0));
                trie.insert(key, value);
            }
        }

        initTime = System.currentTimeMillis() - startTime;
        return trie;
    }

    public long getInitTime() {
        return initTime;
    }
}