package com.concurrentwordcounter.processor;

import static com.concurrentwordcounter.ConcurrentWordCounter.outputFilePath;
import static com.concurrentwordcounter.ConcurrentWordCounter.totalCharacterCount;
import static com.concurrentwordcounter.ConcurrentWordCounter.wordCount;
import com.concurrentwordcounter.tasks.OutputWriter;
import com.concurrentwordcounter.threading.ThreadDelegator;

public class WordCountProcessor {
    public static void processFiles(){
        ThreadDelegator.delegateTasks();
        OutputWriter.outputWordsToFile(outputFilePath, wordCount, totalCharacterCount);
    }
}
