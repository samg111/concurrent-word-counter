package com.concurrentwordcounter;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import com.concurrentwordcounter.runners.GuiRunner;
import com.concurrentwordcounter.runners.HeadlessRunner;
import com.concurrentwordcounter.tasks.OutputWriter;
import com.concurrentwordcounter.tasks.WordCounter;


public class MultiThreadWordCount {
    static ConcurrentHashMap<String, Integer> wordCount;
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--headless")) {
            HeadlessRunner.run();
        } else {
            GuiRunner.launch();
        }

        wordCount = new ConcurrentHashMap<>();
        File inputDirectory = new File("input_files");
        File[] files = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        Thread[] threads = new Thread[files.length];
        // System.out.println("files length: " + files.length + " threads length: " + threads.length);

        for (int i = 0; i < files.length; i++) {
            threads[i] = new Thread(new WordCounter(files[i], wordCount));
            threads[i].start();
        }
        for  (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
        OutputWriter.outputWordsToFile("word_count_output.txt", wordCount);
    }
}