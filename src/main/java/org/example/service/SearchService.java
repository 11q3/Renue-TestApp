package org.example.service;

import org.example.util.Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SearchService {
    private static long initTime;
    Runtime rn = Runtime.getRuntime();

    public Trie initialize(String dataFilePath, int indexedColumnId) throws IOException {
        long startTime = System.currentTimeMillis();
        Trie trie = new Trie();
        System.out.println(Double.parseDouble(String.valueOf((rn.totalMemory() - rn.freeMemory() - 7*1024*1024)))/1024/1024);

        try (BufferedReader br = new BufferedReader(new FileReader(dataFilePath))) {
            StringBuilder values = new StringBuilder();
            List<String> valuesList;
            String line;

            while ((line = br.readLine())!= null) {

                values.setLength(0);

                for (String s : line.replace("\"", "").split(",")) {
                    values.append(s).append(",");
                }

                valuesList = List.of(values.toString().trim().split(","));
                String key = valuesList.get(indexedColumnId);
                int value = Integer.parseInt(valuesList.get(0));

                trie.insert(key, value);

            }
        }
        System.out.println(Double.parseDouble(String.valueOf((rn.totalMemory() - rn.freeMemory() - 7*1024*1024)))/1024/1024);

        initTime = System.currentTimeMillis() - startTime;
        return trie;
    }

    public long getInitTime() {
        return initTime;
    }
}