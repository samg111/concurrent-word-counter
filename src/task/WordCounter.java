package task;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
        try {
            countWordsInFile(file, wordCount);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void countWordsInFile(File file, ConcurrentHashMap<String, Integer> wordCount) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase().replaceAll("[^a-z]", "");
                if (!word.isEmpty()) {
                    wordCount.merge(word, 1, Integer::sum);
                }
            }
        }
    }
}
