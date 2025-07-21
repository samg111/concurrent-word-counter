package com.concurrentwordcounter.tasks;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WordCounter implements Runnable {
    private final File file;
    private final ConcurrentHashMap<String, Integer> wordCount;
    private final AtomicInteger totalCharacterCount;

    public WordCounter(File file, ConcurrentHashMap<String, Integer> wordCount, AtomicInteger totalCharacterCount) {
        this.file = file;
        this.wordCount = wordCount;
        this.totalCharacterCount = totalCharacterCount;
    }

    @Override
    public void run() {
        try {
            countWordsInFile(file, wordCount, totalCharacterCount);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void countWordsInFile(File file, ConcurrentHashMap<String, Integer> wordCount, AtomicInteger totalCharacterCount) throws FileNotFoundException {
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
