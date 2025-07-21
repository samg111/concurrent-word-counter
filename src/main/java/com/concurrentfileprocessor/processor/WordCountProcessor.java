package com.concurrentfileprocessor.processor;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilePath;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.totalCharacterCount;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.wordCount;
import com.concurrentfileprocessor.tasks.OutputWriter;
import com.concurrentfileprocessor.threading.ThreadDelegator;

public class WordCountProcessor {
    public static void processFiles(){
        ThreadDelegator.delegateTasks();
        OutputWriter.outputWordsToFile(outputFilePath, wordCount, totalCharacterCount);
    }
}
