
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class MultiThreadWordCount {
    static ConcurrentHashMap<String, Integer> wordCount;
    public static void main(String[] args) {
        wordCount = new ConcurrentHashMap<>();

        System.out.println("pwd: " + System.getProperty("user.dir"));

        File inputDirectory = new File("input_files");
        File[] files = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        Thread[] threads = new Thread[files.length];
        System.out.println("files length: " + files.length + " threads length: " + threads.length);

        for (int i = 0; i < files.length; i++) {
            threads[i] = new Thread(new WordCounter(files[i], wordCount));
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

    private static class WordCounter implements Runnable {
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
}