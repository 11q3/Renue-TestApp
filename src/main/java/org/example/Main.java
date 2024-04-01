package org.example;


import org.example.model.SearchResult;
import org.example.service.AirportSearch;
import org.example.util.FileUtils;
import org.example.util.Trie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 8) {
            System.err.println("Usage: java " +
                    "-jar AirportSearch.jar " +
                    "--data <csv-file> --indexed-column-id <column-id> " +
                    "--input-file <input-file.txt> " +
                    "--output-file <output-file.json>");
            return;
        }

        String csvFile = args[1];
        int indexedColumnId = Integer.parseInt(args[3]);
        String inputFilePath = args[5];
        String outputFilePath = args[7];

        AirportSearch airportSearch = new AirportSearch();
        Trie trie = airportSearch.initialize(csvFile, indexedColumnId);
        System.out.println(trie.search("Bow")); // [4275, 7848, 3600]

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            List<SearchResult> results = new ArrayList<>();
            String line;
            long startTime;

            while ((line = reader.readLine()) != null) {
                startTime = System.currentTimeMillis();
                List<Integer> matches = trie.search(line);
                results.add(new SearchResult(line, matches, System.currentTimeMillis() - startTime));
            }

            FileUtils.outputResults(outputFilePath, results, airportSearch.getInitTime());
        }
    }
}