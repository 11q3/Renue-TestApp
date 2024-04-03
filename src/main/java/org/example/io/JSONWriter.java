package org.example.io;

import org.example.data.SearchResult;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class JSONWriter {
    /**
     * Outputs the search results to a JSON file.
     *
     * @param outputFile   the path of the output file
     * @param results     the search results to be written to the file
     * @param initTime    the time when the search was initiated
     * @throws IOException if there is an error writing to the file
     */
    public static void outputResults(String outputFile, List<SearchResult> results, long initTime) throws IOException {
        // Create the BufferedWriter using try-with-resources to automatically close it
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {
            // Write the JSON header
            writer.write("{\"initTime\": ");
            writer.write(String.valueOf(initTime));
            writer.write(", \"result\": [");

            // Iterate through the results list and write each search result as a JSON object
            Iterator<SearchResult> iterator = results.iterator();
            while (iterator.hasNext()) {
                SearchResult result = iterator.next();
                writer.write("{\"search\": \"");
                writer.write(result.search);
                writer.write("\", \"result\": ");
                writer.write(String.valueOf(result.result));
                writer.write(", \"time\": ");
                writer.write(String.valueOf(result.time));

                // If there is another element in the list, write a comma to separate the JSON objects
                if (iterator.hasNext()) {
                    writer.write(",");
                }

                writer.newLine();
            }

            // Write the JSON footer
            writer.write("]}");
        }
    }
}