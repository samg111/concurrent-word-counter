package com.concurrentfileprocessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilename;
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
        outputFilename = "test_output_file.txt";
    }

    @AfterEach
    @SuppressWarnings("unused")
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
        if (tempFile != null) {
            File outputFile = new File(tempFile.getParent(), outputFilename);
            if (outputFile.exists()) {
                outputFile.delete();
            }
        }
    }

    @Test
    void testOutputWordsToFile() throws IOException {
        fileStats = new FileStats(wordCount, totalCharacterCount, new AtomicInteger(0));
        fileStats.numberOfFiles = 2;
        String outputDir = tempFile.getParent();
        OutputWriter.outputStatsToFile(outputDir, outputFilename, fileStats);
        
        File actualOutputFile = new File(outputDir, outputFilename);
        List<String> lines = Files.readAllLines(actualOutputFile.toPath());
        assertEquals(6, lines.size());
        assertTrue(lines.get(0).startsWith("Number of files:"));
        assertEquals("Total character count: 15", lines.get(1));
        assertEquals("Total line count: 0", lines.get(2));
        assertTrue(lines.get(3).startsWith("Total word count: 2"));
        assertTrue(lines.contains("hello: 2"));
        assertTrue(lines.contains("world: 1"));
    }

    @Test
    void testOutputStatsToFileExceptionHandling() {
        // invalid directory path to trigger exception
        String invalidPath = "/invalid/path/that/does/not/exist";
        OutputWriter.outputStatsToFile(invalidPath, outputFilename, fileStats);
    }
} 
