package com.concurrentfileprocessor.threading;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.inputFiles;
import com.concurrentfileprocessor.tasks.FileStatCounter;

public class ThreadDelegator {
    public static void delegateTasks(){
        int numberOfFiles = inputFiles.size();
        fileStats.numberOfFiles = numberOfFiles;
        Thread[] threads = new Thread[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
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
