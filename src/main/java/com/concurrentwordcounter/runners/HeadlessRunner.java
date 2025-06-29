package com.concurrentwordcounter.runners;

import com.concurrentwordcounter.processor.WordCountProcessor;

public class HeadlessRunner {
    public static void run() {
        System.out.println("Running in headless mode");
        WordCountProcessor.processFiles();
        // ThreadDelegator.delegateTasks();
        // OutputWriter.outputWordsToFile("word_count_output.txt", wordCount);

    }
    
}
