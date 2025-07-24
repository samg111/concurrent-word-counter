package com.concurrentfileprocessor.tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.concurrentfileprocessor.FileStats;

public class FileMetricsCollector implements Runnable {
    private final File file;
    private final ConcurrentHashMap<String, Integer> wordCount;
    private final AtomicInteger totalCharacterCount;

    public FileMetricsCollector(File file, FileStats fileStats) {
        this.file = file;
        this.wordCount = fileStats.wordCount;
        this.totalCharacterCount = fileStats.totalCharacterCount;
    }

    @Override
    public void run() {
        try {
            countFileComponents(file, wordCount, totalCharacterCount);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void countFileComponents(File file, ConcurrentHashMap<String, Integer> wordCount, AtomicInteger totalCharacterCount) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");
                if (!word.isEmpty()) {
                    wordCount.merge(word, 1, Integer::sum);
                    totalCharacterCount.addAndGet(word.length());
                }
            }
        }
    }
}
