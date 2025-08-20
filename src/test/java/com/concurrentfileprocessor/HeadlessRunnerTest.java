package com.concurrentfileprocessor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.concurrentfileprocessor.runners.HeadlessRunner;

class HeadlessRunnerTest {

    private File tempDir;
    private File txtFile1;
    private File txtFile2;
    private File nonTxtFile;

    @BeforeEach
    void setUp() throws IOException {
        // temp working directory
        tempDir = File.createTempFile("testdir", "");
        tempDir.delete();
        tempDir.mkdir();
        
        // test txt files
        txtFile1 = new File(tempDir, "test1.txt");
        try (PrintWriter writer = new PrintWriter(txtFile1)) {
            writer.println("test content");
        }
        txtFile2 = new File(tempDir, "test2.txt");
        try (PrintWriter writer = new PrintWriter(txtFile2)) {
            writer.println("more content");
        }
        
        // test non txt file
        nonTxtFile = new File(tempDir, "test.doc");
        try (PrintWriter writer = new PrintWriter(nonTxtFile)) {
            writer.println("document content");
        }
    }

    @AfterEach
    void tearDown() {
        if (txtFile1 != null && txtFile1.exists()) txtFile1.delete();
        if (txtFile2 != null && txtFile2.exists()) txtFile2.delete();
        if (nonTxtFile != null && nonTxtFile.exists()) nonTxtFile.delete();
        if (tempDir != null && tempDir.exists()) tempDir.delete();
    }

    @Test
    void testGetInputFiles() throws Exception {
        // get private method
        java.lang.reflect.Method method = HeadlessRunner.class.getDeclaredMethod("getInputFiles", File.class);
        method.setAccessible(true);
        
        // invoke method
        @SuppressWarnings("unchecked")
        List<File> result = (List<File>) method.invoke(null, tempDir);
        
        // verify the amount of files and if they are txt files
        assertEquals(2, result.size());
        assertTrue(result.contains(txtFile1));
        assertTrue(result.contains(txtFile2));
    }

    @Test
    void testGetOutputFilename() throws Exception {
        // get private method
        java.lang.reflect.Method method = HeadlessRunner.class.getDeclaredMethod("getOutputFilename");
        method.setAccessible(true);
        
        // test custom filename
        System.setIn(new java.io.ByteArrayInputStream("custom_output.txt\n".getBytes()));
        String customResult = (String) method.invoke(null);
        assertEquals("custom_output.txt", customResult);
        
        // test default filename (empty input)
        System.setIn(new java.io.ByteArrayInputStream("\n".getBytes()));
        String defaultResult = (String) method.invoke(null);
        assertEquals("processed_file_stats.txt", defaultResult);
    }
}
