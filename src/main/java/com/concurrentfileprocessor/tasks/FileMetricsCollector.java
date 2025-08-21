package com.concurrentfileprocessor.tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.concurrentfileprocessor.FileStats;

public class FileMetricsCollector implements Runnable {
    private final File file;
    private final FileStats fileStats;

    public FileMetricsCollector(File file, FileStats fileStats) {
        this.file = file;
        this.fileStats = fileStats;
    }

    @Override
    public void run() {
        try {
            countFileComponents(file, fileStats);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void countFileComponents(File file, FileStats fileStats) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            int fileLineCount = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileLineCount++;
                
                String[] words = line.split("\\s+");
                for (String word : words) {
                    String cleanWord = word.toLowerCase().replaceAll("[^a-z]", "");
                    if (!cleanWord.isEmpty()) {
                        fileStats.wordCount.merge(cleanWord, 1, Integer::sum);
                        fileStats.characterCount.addAndGet(cleanWord.length());
                    }  
                }
            }
            fileStats.lineCount.addAndGet(fileLineCount);
        }
    }
}
