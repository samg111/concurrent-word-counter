package com.concurrentfileprocessor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.inputFiles;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilePath;
import com.concurrentfileprocessor.processor.FileProcessor;

class WordCountProcessorTest {
    private List<File> tempFiles;
    private File outputFile;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() throws IOException {
        tempFiles = new ArrayList<>();
        File file1 = File.createTempFile("processor1", ".txt");
        try (PrintWriter out = new PrintWriter(file1)) {
            out.println("cat dog cat");
        }
        tempFiles.add(file1);
        File file2 = File.createTempFile("processor2", ".txt");
        try (PrintWriter out = new PrintWriter(file2)) {
            out.println("dog mouse cat");
        }
        tempFiles.add(file2);
        inputFiles = tempFiles;
        fileStats = new FileStats(new ConcurrentHashMap<>(), new AtomicInteger(0), new AtomicInteger(0));
        outputFile = File.createTempFile("output", ".txt");
        outputFilePath = outputFile.getAbsolutePath();
    }

    @AfterEach
    @SuppressWarnings("unused")
    void tearDown() {
        for (File file : tempFiles) {
            if (file.exists()) file.delete();
        }
        if (outputFile != null && outputFile.exists()) {
            outputFile.delete();
        }
    }

    @Test
    void testProcessFilesIntegration() throws IOException {
        FileProcessor.processFiles();
        List<String> lines = Files.readAllLines(outputFile.toPath());
        assertEquals(7, lines.size());
        assertEquals("Number of files: 2", lines.get(0));
        assertTrue(lines.get(1).startsWith("Total character count:"));
        assertTrue(lines.get(2).startsWith("Total line count: 2"));
        assertTrue(lines.get(3).startsWith("Total word count: 3"));
        assertTrue(lines.contains("cat: 3"));
        assertTrue(lines.contains("dog: 2"));
        assertTrue(lines.contains("mouse: 1"));
    }
} 
