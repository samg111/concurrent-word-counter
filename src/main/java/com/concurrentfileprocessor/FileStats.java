package com.concurrentfileprocessor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FileStats {
    public ConcurrentHashMap<String, Integer> wordCount;
    public AtomicInteger totalCharacterCount;

    public FileStats(ConcurrentHashMap<String, Integer> wordCount, AtomicInteger totalCharacterCount) {
        this.wordCount = wordCount;
        this.totalCharacterCount = totalCharacterCount;
    }

    public FileStats() {}
}
