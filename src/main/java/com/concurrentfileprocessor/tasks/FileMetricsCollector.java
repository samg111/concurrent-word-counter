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
    private final AtomicInteger characterCount;
    private final AtomicInteger lineCount;

    public FileMetricsCollector(File file, FileStats fileStats) {
        this.file = file;
        this.wordCount = fileStats.wordCount;
        this.characterCount = fileStats.characterCount;
        this.lineCount = fileStats.lineCount;
    }

    @Override
    public void run() {
        try {
            countFileComponents(file, wordCount, characterCount, lineCount);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void countFileComponents(File file, ConcurrentHashMap<String, Integer> wordCount, AtomicInteger characterCount, AtomicInteger lineCount) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            int fileLineCount = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileLineCount++;
                Scanner wordScanner = new Scanner(line);
                while (wordScanner.hasNext()) {
                    String word = wordScanner.next().toLowerCase().replaceAll("[^a-z]", "");
                    if (!word.isEmpty()) {
                        wordCount.merge(word, 1, Integer::sum);
                        characterCount.addAndGet(word.length());
                    }
                }
                wordScanner.close();
            }
            lineCount.addAndGet(fileLineCount);
        }
    }
}
