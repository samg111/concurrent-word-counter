package com.concurrentfileprocessor.tasks;

import java.io.FileNotFoundException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OutputWriter {
    public static void outputStatsToFile(String outputFilePath, ConcurrentHashMap<String, Integer> wordCount, AtomicInteger totalCharacterCount) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(outputFilePath)) {
            writer.println("Total character count: " + totalCharacterCount.get());
            for (String word : wordCount.keySet()) {
                writer.println(word + ": " + wordCount.get(word));
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
