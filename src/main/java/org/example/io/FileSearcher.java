package org.example.io;

import org.example.data.SearchResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileSearcher {
    public static List<SearchResult> searchInFile(String inputFilePath, Map<String, List<Integer>> data) {
        if (inputFilePath == null || data == null) {
            throw new IllegalArgumentException("Input file path or data cannot be null.");
        }

        List<SearchResult> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;

            while ((line = br.readLine())!= null) {
                long startTime = System.currentTimeMillis();
                List<Integer> matches = new ArrayList<>();

                for (Map.Entry<String, List<Integer>> entry : data.entrySet()) {
                    if (entry.getKey().startsWith(line)) {
                        matches.addAll(entry.getValue());
                    }
                }

                if (!matches.isEmpty()) {
                    results.add(new SearchResult(line, matches, System.currentTimeMillis() - startTime));
                } else {
                    results.add(new SearchResult(line, Collections.emptyList(), System.currentTimeMillis() - startTime));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}