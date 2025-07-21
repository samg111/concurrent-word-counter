package com.concurrentwordcounter.runners;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import com.concurrentwordcounter.ConcurrentWordCounter;
import com.concurrentwordcounter.processor.WordCountProcessor;

public class HeadlessRunner {
    public static void run() {
        System.out.println("Running in headless mode");
        
        String outputFileName = getOutputFileName();
        ConcurrentWordCounter.outputFilePath = outputFileName;
        
        File inputDirectory = new File("input_files");
        if (inputDirectory.exists() && inputDirectory.isDirectory()) {
            File[] files = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            if (files != null) {
                ConcurrentWordCounter.inputFiles.addAll(Arrays.asList(files));
                System.out.println("Loaded " + ConcurrentWordCounter.inputFiles.size() + " input files");
            }
        }
        
        if (ConcurrentWordCounter.inputFiles.isEmpty()) {
            System.err.println("No input files found in input_files directory");
            return;
        }
        
        WordCountProcessor.processFiles();
        System.out.println("Total character count: " + ConcurrentWordCounter.totalCharacterCount.get());
    }
    
    private static String getOutputFileName() {
        String fileName;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter output file name (default: word_count_output.txt): ");
            fileName = scanner.nextLine().trim();
        }
        
        if (fileName.isEmpty()) {
            fileName = "word_count_output.txt";
            System.out.println("Using default file name: " + fileName);
        }
        
        return fileName;
    }
}
