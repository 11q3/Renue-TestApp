package org.example.io;

import org.example.data.SearchResult;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class JSONWriter {
    public static void outputResults(String outputFile, List<SearchResult> results, long initTime) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {
            writer.write("{\"initTime\": ");
            writer.write(String.valueOf(initTime));
            writer.write(", \"result\":[");
            writer.newLine();

            Iterator<SearchResult> iterator = results.iterator();
            while (iterator.hasNext()) {
                SearchResult result = iterator.next();
                writer.write("    {\"search\": \"");
                writer.write(result.search);
                writer.write("\", \"result\": [");
                writer.write(result.result.toString()
                        .replace("[", "")
                        .replace("]", ""));
                writer.write("],");
                writer.write("\"time\": ");


                writer.write(String.valueOf(result.time));
                writer.write("}");

                if (iterator.hasNext()) {
                    writer.write(",");
                }

                writer.newLine();
            }
            writer.write("]}");
            writer.newLine();
        }
    }
}