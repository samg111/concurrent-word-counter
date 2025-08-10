package com.concurrentfileprocessor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FileStats {
    public ConcurrentHashMap<String, Integer> wordCount;
    public AtomicInteger characterCount;
    public AtomicInteger lineCount;
    public int numberOfFiles;
    

    public FileStats(ConcurrentHashMap<String, Integer> wordCount, AtomicInteger characterCount, AtomicInteger lineCount) {
        this.wordCount = wordCount;
        this.characterCount = characterCount;
        this.lineCount = lineCount;
    }

    public FileStats() {}

    public FileStats refreshFileStats(FileStats stats){
        stats.wordCount = new ConcurrentHashMap<>();
        stats.characterCount = new AtomicInteger(0);
        stats.lineCount = new AtomicInteger(0);
        return stats;
    }
}
