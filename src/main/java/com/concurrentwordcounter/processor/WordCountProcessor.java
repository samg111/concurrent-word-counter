package com.concurrentwordcounter.processor;

import static com.concurrentwordcounter.ConcurrentWordCounter.wordCount;
import com.concurrentwordcounter.tasks.OutputWriter;
import com.concurrentwordcounter.threading.ThreadDelegator;

public class WordCountProcessor {
    public static void processFiles(){
        ThreadDelegator.delegateTasks();
        OutputWriter.outputWordsToFile("word_count_output.txt", wordCount);
    }
}
