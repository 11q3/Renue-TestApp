package org.example.io;

import org.example.data.SearchResult;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JSONWriter {
    public static void outputResults(String outputFile, List<SearchResult> results, long initTime) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {
            writer.write("{\"initTime\": " + initTime + ", \"result\": [");
            for (int i = 0; i < results.size(); i++) {
                SearchResult result = results.get(i);
                writer.write("{\"search\": \"" + result.search +
                        "\", \"result\": " + result.result +
                        ", \"time\": " + result.time + "}");
                if (i < results.size() - 1) {
                    writer.write(",");
                }
                writer.newLine();
            }
            writer.write("]}");
        }
    }
}
