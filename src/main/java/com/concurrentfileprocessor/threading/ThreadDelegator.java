package com.concurrentfileprocessor.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.inputFiles;
import com.concurrentfileprocessor.tasks.FileMetricsCollector;

public class ThreadDelegator {
    public static void delegateTasks() {
        int numberOfFiles = inputFiles.size();
        fileStats.numberOfFiles = numberOfFiles;
        
        ExecutorService executor = Executors.newWorkStealingPool();
        
        for (int i = 0; i < numberOfFiles; i++) {
            executor.submit(new FileMetricsCollector(inputFiles.get(i), fileStats));
        }
        
        // Wait for completion and cleanup
        executor.shutdown();
        try {
            executor.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
