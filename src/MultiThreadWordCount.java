
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class MultiThreadWordCount {
    static ConcurrentHashMap<String, Integer> wordCount;
    public static void main(String[] args) {
        // int numOfThreads = 5;
        wordCount = new ConcurrentHashMap<>();

        // String inputFilesDirectory = "input_files/";
        System.out.println("pwd: " + System.getProperty("user.dir"));

        File inputDirectory = new File("input_files");
        File[] files = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        for (Object file : files) {
            System.out.println("file: " + file.toString());
        }
    }
}