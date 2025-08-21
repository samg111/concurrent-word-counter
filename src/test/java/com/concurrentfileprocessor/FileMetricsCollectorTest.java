package com.concurrentfileprocessor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.concurrentfileprocessor.tasks.FileMetricsCollector;

class FileMetricsCollectorTest {

    private File tempFile;
    private FileStats fileStats;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() throws IOException {
        tempFile = File.createTempFile("testfile", ".txt");
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("test content");
        }
        
        fileStats = new FileStats(new java.util.concurrent.ConcurrentHashMap<>(), new java.util.concurrent.atomic.AtomicInteger(0), new java.util.concurrent.atomic.AtomicInteger(0));
    }

    @AfterEach
    @SuppressWarnings("unused")
    void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testCountFileComponents() throws Exception {
        // test the static method directly
        FileMetricsCollector.countFileComponents(tempFile, fileStats);
        
        // verify metrics were collected
        assertEquals(2, fileStats.wordCount.size());
        assertEquals(11, fileStats.characterCount.get());
        assertEquals(1, fileStats.lineCount.get());
    }
}
