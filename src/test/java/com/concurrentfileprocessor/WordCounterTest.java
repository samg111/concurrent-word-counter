package com.concurrentfileprocessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.concurrentfileprocessor.tasks.WordCounter;

class WordCounterTest {
    private File tempFile;
    private ConcurrentHashMap<String, Integer> wordCount;
    private AtomicInteger totalCharacterCount;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() throws IOException {
        tempFile = File.createTempFile("testfile", ".txt");
        try (PrintWriter out = new PrintWriter(tempFile)) {
            out.println("Hello world hello");
        }
        wordCount = new ConcurrentHashMap<>();
        totalCharacterCount = new AtomicInteger(0);
    }

    @AfterEach
    @SuppressWarnings("unused")
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testCountWordsInFile() throws FileNotFoundException {
        WordCounter.countWordsInFile(tempFile, wordCount, totalCharacterCount);
        assertEquals(2, wordCount.size());
        assertEquals(2, wordCount.get("hello"));
        assertEquals(1, wordCount.get("world"));
        assertEquals(15, totalCharacterCount.get()); // "hello" (5) + "world" (5) + "hello" (5)
    }

    @Test
    void testTotalCharacterCountThreadSafety() throws Exception {
        File tempFile1 = File.createTempFile("testfile1", ".txt");
        File tempFile2 = File.createTempFile("testfile2", ".txt");
        try (PrintWriter out1 = new PrintWriter(tempFile1); PrintWriter out2 = new PrintWriter(tempFile2)) {
            out1.println("Alpha beta gamma"); // 5 + 4 + 5 = 14
            out2.println("Delta epsilon");    // 5 + 7 = 12
        }
        ConcurrentHashMap<String, Integer> wc = new ConcurrentHashMap<>();
        AtomicInteger tc = new AtomicInteger(0);
        Thread t1 = new Thread(() -> {
            try { WordCounter.countWordsInFile(tempFile1, wc, tc); } catch (FileNotFoundException ignored) {}
        });
        Thread t2 = new Thread(() -> {
            try { WordCounter.countWordsInFile(tempFile2, wc, tc); } catch (FileNotFoundException ignored) {}
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertEquals(26, tc.get()); // 14 + 12
        tempFile1.delete();
        tempFile2.delete();
    }
} 
