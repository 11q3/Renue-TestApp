package org.example.io;

import org.example.data.SearchResult;
import org.example.util.Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InputReader {
    public static List<SearchResult> readInput(String inputFilePath, Trie trie) throws IOException {
        if (inputFilePath == null || trie == null) {
            throw new IllegalArgumentException("Input file path or Trie cannot be null.");
        }

        List<SearchResult> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            long startTime = Instant.now().toEpochMilli();

            while ((line = br.readLine())!= null) {
                List<Short> matches = trie.search(line);
                results.add(new SearchResult(line, matches, Instant.now().toEpochMilli() - startTime));
            }
        }

        return results;
    }
}