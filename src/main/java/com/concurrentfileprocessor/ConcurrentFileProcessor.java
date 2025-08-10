package com.concurrentfileprocessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.concurrentfileprocessor.runners.GuiRunner;
import com.concurrentfileprocessor.runners.HeadlessRunner;

public class ConcurrentFileProcessor {
    public static FileStats fileStats;
    public static List<File> inputFiles;
    public static String outputFilePath;
    
    public static void main(String[] args) {
        fileStats = new FileStats();
        fileStats.wordCount = new ConcurrentHashMap<>();
        fileStats.characterCount = new AtomicInteger(0);
        fileStats.lineCount = new AtomicInteger(0);
        inputFiles = new ArrayList<>();
        outputFilePath = "processed_files_stats.txt";
        if (args.length > 0 && args[0].equals("--headless")) {
            HeadlessRunner.run();
        } else {
            GuiRunner.launch();
        }
    }
}
