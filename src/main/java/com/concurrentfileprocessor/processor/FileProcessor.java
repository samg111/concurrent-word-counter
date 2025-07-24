package com.concurrentfileprocessor.processor;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilePath;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;
import com.concurrentfileprocessor.tasks.OutputWriter;
import com.concurrentfileprocessor.threading.ThreadDelegator;

public class FileProcessor {
    public static void processFiles(){
        ThreadDelegator.delegateTasks();
        OutputWriter.outputStatsToFile(outputFilePath, fileStats);
    }
}
