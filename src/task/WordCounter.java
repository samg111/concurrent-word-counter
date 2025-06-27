package task;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class WordCounter implements Runnable {
    private final File file;
    private final ConcurrentHashMap<String, Integer> wordCount;

    public WordCounter(File file, ConcurrentHashMap<String, Integer> wordCount) {
        this.file = file;
        this.wordCount = wordCount;
    }

    @Override
    public void run() {
        System.out.println("Counting words in file: " + file.getName());
    }
}
