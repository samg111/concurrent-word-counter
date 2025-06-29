package com.concurrentwordcounter.threading;

import static com.concurrentwordcounter.ConcurrentWordCounter.selectedFiles;
import static com.concurrentwordcounter.ConcurrentWordCounter.wordCount;
import com.concurrentwordcounter.tasks.WordCounter;

public class ThreadDelegator {
    public static void delegateTasks(){
        Thread[] threads = new Thread[selectedFiles.size()];
        for (int i = 0; i < selectedFiles.size(); i++) {
            threads[i] = new Thread(new WordCounter(selectedFiles.get(i), wordCount));
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
