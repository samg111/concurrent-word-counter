package com.concurrentfileprocessor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.concurrentfileprocessor.tasks.FileMetricsCollector;

class FileMetricsCollectorTest {

    private File tempFile;
    private FileStats fileStats;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("testfile", ".txt");
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("test content");
        }
        
        fileStats = new FileStats();
        fileStats.wordCount = new ConcurrentHashMap<>();
        fileStats.characterCount = new AtomicInteger(0);
        fileStats.lineCount = new AtomicInteger(0);
    }

    @AfterEach
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testCountFileComponents() throws Exception {
        // test the static method directly
        FileMetricsCollector.countFileComponents(tempFile, fileStats.wordCount, fileStats.characterCount, fileStats.lineCount);
        
        // verify metrics were collected
        assertEquals(2, fileStats.wordCount.size());
        assertEquals(11, fileStats.characterCount.get());
        assertEquals(1, fileStats.lineCount.get());
    }
}
