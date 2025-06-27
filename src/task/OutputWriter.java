package task;

import java.io.FileNotFoundException;
import java.util.concurrent.ConcurrentHashMap;

public class OutputWriter {
    public static void outputWordsToFile(String outputFilePath, ConcurrentHashMap<String, Integer> wordCount) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(outputFilePath)) {
            for (String word : wordCount.keySet()) {
                writer.println(word + ": " + wordCount.get(word));
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
