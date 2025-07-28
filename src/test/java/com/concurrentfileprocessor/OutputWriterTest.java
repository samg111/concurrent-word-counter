package com.concurrentfileprocessor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.concurrentfileprocessor.tasks.OutputWriter;

public class OutputWriterTest {
    private File tempFile;
    private ConcurrentHashMap<String, Integer> wordCount;
    private AtomicInteger totalCharacterCount;
    private FileStats fileStats;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() throws IOException {
        tempFile = File.createTempFile("outputtest", ".txt");
        wordCount = new ConcurrentHashMap<>();
        wordCount.put("hello", 2);
        wordCount.put("world", 1);
        totalCharacterCount = new AtomicInteger(15);
    }

    @AfterEach
    @SuppressWarnings("unused")
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testOutputWordsToFile() throws IOException {
        fileStats = new FileStats(wordCount, totalCharacterCount, new AtomicInteger(0));
        OutputWriter.outputStatsToFile(tempFile.getAbsolutePath(), fileStats);
        List<String> lines = java.nio.file.Files.readAllLines(tempFile.toPath());
        assertEquals(7, lines.size());
        assertTrue(lines.get(0).startsWith("Number of files:"));
        assertEquals("Total character count: 15", lines.get(1));
        assertEquals("Total line count: 0", lines.get(2));
        assertEquals("", lines.get(3));
        assertEquals("Individual word count:", lines.get(4));
        assertTrue(lines.contains("hello: 2"));
        assertTrue(lines.contains("world: 1"));
    }
} 
