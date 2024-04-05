package org.example.io;

import org.example.data.SearchResult;
import org.example.util.Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    public static List<SearchResult> readInput(String inputFilePath, Trie trie) {
        if (inputFilePath == null || trie == null) {
            throw new IllegalArgumentException("Input file path or Trie cannot be null.");
        }

        List<SearchResult> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                long startTime = System.currentTimeMillis();
                List<Short> matches = trie.search(line);

                long endTime = System.currentTimeMillis();
                results.add(new SearchResult(line, matches, endTime - startTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}