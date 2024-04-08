package org.example;

import org.example.data.SearchResult;
import org.example.io.FileSearcher;
import org.example.io.JSONWriter;
import org.example.service.SearchService;

import java.util.List;

import java.io.IOException;
import java.util.*;

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
        int indexedColumnId = Integer.parseInt(args[3]) - 1;
        String inputFilePath = args[5];
        String outputFilePath = args[7];

        SearchService searchService = new SearchService();
        Map<String, List<Integer>> data = searchService.initialize(csvFile, indexedColumnId);

        List<SearchResult> results = FileSearcher.searchInFile(inputFilePath, data);
        JSONWriter.outputResults(outputFilePath, results, searchService.getInitTime());
    }
}