package com.concurrentwordcounter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.concurrentwordcounter.runners.GuiRunner;
import com.concurrentwordcounter.runners.HeadlessRunner;

public class ConcurrentWordCounter {
    public static ConcurrentHashMap<String, Integer> wordCount;
    public static List<File> inputFiles;
    public static String outputFilePath;
    public static AtomicInteger totalCharacterCount;
    public static void main(String[] args) {
        wordCount = new ConcurrentHashMap<>();
        inputFiles = new ArrayList<>();
        totalCharacterCount = new AtomicInteger(0);
        if (args.length > 0 && args[0].equals("--headless")) {
            HeadlessRunner.run();
        } else {
            GuiRunner.launch();
        }
    }
}
