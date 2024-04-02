package org.example.io;

import org.example.data.SearchResult;
import org.example.util.Trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    public static List<SearchResult> readInput(String inputFilePath, Trie trie) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        List<SearchResult> results = new ArrayList<>();

        String line;
        long startTime;

        while ((line = reader.readLine()) != null) {
            startTime = System.currentTimeMillis();
            List<Integer> matches = trie.search(line);
            results.add(new SearchResult(line, matches, System.currentTimeMillis() - startTime));
        }
        return results;
    }
}
