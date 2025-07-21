package com.concurrentwordcounter;

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

import com.concurrentwordcounter.tasks.WordCounter;

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
} 
