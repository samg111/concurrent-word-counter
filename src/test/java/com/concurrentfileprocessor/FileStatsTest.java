package com.concurrentfileprocessor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class FileStatsTest {

    @Test
    void testRefreshFileStats() {
        ConcurrentHashMap<String, Integer> initialWordCount = new ConcurrentHashMap<>();
        initialWordCount.put("test", 5);
        AtomicInteger initialCharacterCount = new AtomicInteger(100);
        AtomicInteger initialLineCount = new AtomicInteger(10); 
        FileStats stats = new FileStats(initialWordCount, initialCharacterCount, initialLineCount);
        
        // refresh the stats
        FileStats refreshedStats = stats.refreshFileStats(stats);
        
        // verify the stats are refreshed
        assertEquals(0, refreshedStats.wordCount.size());
        assertEquals(0, refreshedStats.characterCount.get());
        assertEquals(0, refreshedStats.lineCount.get());
        
        //  verifies object exists
        assertNotNull(refreshedStats);
    }
}
