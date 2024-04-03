package org.example.io;

import org.example.data.SearchResult;
import org.example.util.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to read input and search for matches using a given Trie.
 */
public class InputReader {

    /**
     * Reads input from a file and returns a list of SearchResult objects.
     *
     * @param inputFilePath The path to the input file.
     * @param trie          The Trie to search for matches.
     * @return A list of SearchResult objects.
     * @throws IOException If there is an error reading the input file.
     */
    public static List<SearchResult> readInput(String inputFilePath, Trie trie) throws IOException {
        if (inputFilePath == null || trie == null) {
            throw new IllegalArgumentException("Input file path and Trie cannot be null.");
        }

        List<SearchResult> results = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFilePath))) {
            String line;
            long startTime;

            while ((line = reader.readLine()) != null) {
                startTime = Instant.now().toEpochMilli();
                List<Integer> matches = trie.search(line);
                results.add(new SearchResult(line, matches, Instant.now().toEpochMilli() - startTime));
            }
        }

        return results;
    }
}