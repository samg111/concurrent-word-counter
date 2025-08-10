package com.concurrentfileprocessor.tasks;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.concurrentfileprocessor.FileStats;

public class OutputWriter {
    public static void outputStatsToFile(String outputFilePath, FileStats fileStats) {
        try (PrintWriter writer = new PrintWriter(outputFilePath)) {
            writer.println("Number of files: " + fileStats.numberOfFiles);
            writer.println("Total character count: " + fileStats.characterCount.get());
            writer.println("Total line count: " + fileStats.lineCount.get());    
            writer.println("Total word count: " + fileStats.wordCount.size());

            for (String word : fileStats.wordCount.keySet()) {
                writer.println(word + ": " + fileStats.wordCount.get(word));
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
