package com.concurrentwordcounter.threading;

import static com.concurrentwordcounter.ConcurrentWordCounter.inputFiles;
import static com.concurrentwordcounter.ConcurrentWordCounter.totalCharacterCount;
import static com.concurrentwordcounter.ConcurrentWordCounter.wordCount;
import com.concurrentwordcounter.tasks.WordCounter;

public class ThreadDelegator {
    public static void delegateTasks(){
        Thread[] threads = new Thread[inputFiles.size()];
        for (int i = 0; i < inputFiles.size(); i++) {
            threads[i] = new Thread(new WordCounter(inputFiles.get(i), wordCount, totalCharacterCount));
            threads[i].start();
        }
        for  (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}
