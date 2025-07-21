package com.concurrentwordcounter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.concurrentwordcounter.processor.WordCountProcessor;

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
        com.concurrentwordcounter.ConcurrentWordCounter.inputFiles = tempFiles;
        com.concurrentwordcounter.ConcurrentWordCounter.wordCount = new ConcurrentHashMap<>();
        outputFile = File.createTempFile("output", ".txt");
        com.concurrentwordcounter.ConcurrentWordCounter.outputFilePath = outputFile.getAbsolutePath();
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
        WordCountProcessor.processFiles();
        List<String> lines = Files.readAllLines(outputFile.toPath());
        assertEquals(3, lines.size());
        assertTrue(lines.contains("cat: 3"));
        assertTrue(lines.contains("dog: 2"));
        assertTrue(lines.contains("mouse: 1"));
    }
} 
