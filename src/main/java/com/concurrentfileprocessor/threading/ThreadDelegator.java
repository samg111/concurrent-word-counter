package com.concurrentfileprocessor.threading;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.inputFiles;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;
import com.concurrentfileprocessor.tasks.FileStatCounter;

public class ThreadDelegator {
    public static void delegateTasks(){
        Thread[] threads = new Thread[inputFiles.size()];
        for (int i = 0; i < inputFiles.size(); i++) {
            threads[i] = new Thread(new FileStatCounter(inputFiles.get(i), fileStats));
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
