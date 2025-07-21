package com.concurrentwordcounter;

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

import com.concurrentwordcounter.tasks.OutputWriter;

public class OutputWriterTest {
    private File tempFile;
    private ConcurrentHashMap<String, Integer> wordCount;
    private AtomicInteger totalCharacterCount;

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
        OutputWriter.outputWordsToFile(tempFile.getAbsolutePath(), wordCount, totalCharacterCount);
        List<String> lines = java.nio.file.Files.readAllLines(tempFile.toPath());
        assertEquals(3, lines.size());
        assertTrue(lines.get(0).equals("Total character count: 15"));
        assertTrue(lines.contains("hello: 2"));
        assertTrue(lines.contains("world: 1"));
    }
} 
