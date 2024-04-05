package org.example;

import org.example.data.SearchResult;
import org.example.io.InputReader;
import org.example.io.JSONWriter;
import org.example.service.SearchService;
import org.example.util.Trie;

import java.io.*;
import java.lang.management.ManagementFactory;
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
        int indexedColumnId = Integer.parseInt(args[3])-1;
        String inputFilePath = args[5];
        String outputFilePath = args[7];

        SearchService searchService = new SearchService();
        Trie trie = searchService.initialize(csvFile, indexedColumnId);

        System.out.println("Used memory in heap: " + (double) (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()) / 1_048_576.0 + " MB");
        System.out.println("Free memory in heap: " + ((double) (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax()) / 1_048_576.0 -
                (double) (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()) / 1_048_576.0) + " MB");
        System.out.println();


        List<SearchResult> results =
                InputReader.readInput(inputFilePath, trie);
        JSONWriter.outputResults(outputFilePath, results, searchService.getInitTime());

    }
}