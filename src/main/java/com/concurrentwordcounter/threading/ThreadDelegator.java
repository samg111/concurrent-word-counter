package com.concurrentwordcounter.threading;

import java.io.File;

import static com.concurrentwordcounter.MultiThreadWordCount.wordCount;
import com.concurrentwordcounter.tasks.OutputWriter;
import com.concurrentwordcounter.tasks.WordCounter;

public class ThreadDelegator {
    public static void delegateTasks(){
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
