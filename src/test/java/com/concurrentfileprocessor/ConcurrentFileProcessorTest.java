package com.concurrentfileprocessor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class ConcurrentFileProcessorTest {

    @Test
    void testGetDefaultDownloadsPath() {
        String result = ConcurrentFileProcessor.getDefaultDownloadsPath();
        
        // a path is returned
        assertTrue(result.length() > 0);
        
        // path contains the home directory
        String home = System.getProperty("user.home");
        assertTrue(result.contains(home));
        
        // path is the downloads directory
        assertTrue(result.equals(home + "/Downloads"));
    }
}
