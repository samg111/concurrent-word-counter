package com.concurrentwordcounter.threading;

import static com.concurrentwordcounter.ConcurrentWordCounter.selectedFiles;
import static com.concurrentwordcounter.ConcurrentWordCounter.wordCount;
import com.concurrentwordcounter.tasks.OutputWriter;
import com.concurrentwordcounter.tasks.WordCounter;

public class ThreadDelegator {
    public static void delegateTasks(){
        // File inputDirectory = new File("input_files");
        // File[] files = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        Thread[] threads = new Thread[selectedFiles.size()];
        // System.out.println("files length: " + files.length + " threads length: " + threads.length);

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
        OutputWriter.outputWordsToFile("word_count_output.txt", wordCount);
    }
}
