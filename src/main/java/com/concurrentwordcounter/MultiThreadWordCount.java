package com.concurrentwordcounter;

import java.util.concurrent.ConcurrentHashMap;

import com.concurrentwordcounter.runners.GuiRunner;
import com.concurrentwordcounter.runners.HeadlessRunner;

public class MultiThreadWordCount {
    public static ConcurrentHashMap<String, Integer> wordCount;
    public static void main(String[] args) {
        wordCount = new ConcurrentHashMap<>();
        if (args.length > 0 && args[0].equals("--headless")) {
            HeadlessRunner.run();
        } else {
            GuiRunner.launch();
        }
    }
}