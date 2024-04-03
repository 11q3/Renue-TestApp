package org.example.io;

import org.example.data.SearchResult;
import org.example.util.Trie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    public static List<SearchResult> readInput(String inputFilePath, Trie trie) throws IOException {
        if (inputFilePath == null || trie == null) {
            throw new IllegalArgumentException("Input file path or Trie cannot be null.");
        }

        List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
        final long startTime = Instant.now().toEpochMilli();
        List<SearchResult> results = new ArrayList<>();

        for (String line : lines) {
            List<Integer> matches = trie.search(line);
            results.add(new SearchResult(line, matches, Instant.now().toEpochMilli() - startTime));
        }

        return results;
    }
}