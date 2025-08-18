package com.concurrentfileprocessor.runners;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import static com.concurrentfileprocessor.ConcurrentFileProcessor.fileStats;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.inputFiles;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilePath;
import static com.concurrentfileprocessor.ConcurrentFileProcessor.outputFilename;
import com.concurrentfileprocessor.processor.FileProcessor;

public class HeadlessRunner {
    public static void run() {
        System.out.println("Running in headless mode");

        String currentDir = System.getProperty("user.dir");
        File inputDirectory = new File(currentDir);
        outputFilePath = currentDir;

        outputFilename = getOutputFileName();
        
        if (inputDirectory.exists() && inputDirectory.isDirectory()) {
            File[] files = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            if (files != null) {
                inputFiles.addAll(Arrays.asList(files));
                System.out.println("Loaded " + inputFiles.size() + " input files");
            }
        }
        
        if (inputFiles.isEmpty()) {
            System.err.println("No input files found in input_files directory");
            return;
        }
        
        FileProcessor.processFiles();
        System.out.println("Total file count: " + fileStats.numberOfFiles);
        System.out.println("Total character count: " + fileStats.characterCount.get());
        System.out.println("Total line count: " + fileStats.lineCount.get());
        System.out.println("Total word count: " + fileStats.wordCount.size());

        
    }
    
    private static String getOutputFileName() {
        String fileName;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter output file name (default: processed_file_stats.txt): ");
            fileName = scanner.nextLine().trim();
        }
        
        if (fileName.isEmpty()) {
            fileName = "processed_file_stats.txt";
            System.out.println("Using default file name: " + fileName);
        }
        
        return fileName;
    }
}
