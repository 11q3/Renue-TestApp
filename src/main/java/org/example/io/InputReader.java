package org.example.io;

import org.example.data.SearchResult;
import org.example.util.Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    public static List<SearchResult> readInput(String inputFilePath, Trie trie) throws IOException {
        if (inputFilePath == null || trie == null) {
            throw new IllegalArgumentException("Input file path or Trie cannot be null.");
        }

        List<SearchResult> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath), 1)) {
            String line;

            while ((line = br.readLine())!= null) {
                long startTime = Instant.now().toEpochMilli();

                List<Short> matches = trie.search(line);

                long endTime = Instant.now().toEpochMilli();
                results.add(new SearchResult(line, matches, endTime - startTime));
            }}

        return results;
    }
}