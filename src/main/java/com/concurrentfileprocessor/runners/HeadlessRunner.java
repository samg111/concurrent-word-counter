package com.concurrentfileprocessor.runners;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import com.concurrentfileprocessor.ConcurrentFileProcessor;
import com.concurrentfileprocessor.processor.FileProcessor;

public class HeadlessRunner {
    public static void run() {
        System.out.println("Running in headless mode");
        
        String outputFileName = getOutputFileName();
        ConcurrentFileProcessor.outputFilePath = outputFileName;
        
        File inputDirectory = new File("input_files");
        if (inputDirectory.exists() && inputDirectory.isDirectory()) {
            File[] files = inputDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            if (files != null) {
                ConcurrentFileProcessor.inputFiles.addAll(Arrays.asList(files));
                System.out.println("Loaded " + ConcurrentFileProcessor.inputFiles.size() + " input files");
            }
        }
        
        if (ConcurrentFileProcessor.inputFiles.isEmpty()) {
            System.err.println("No input files found in input_files directory");
            return;
        }
        
        FileProcessor.processFiles();
        System.out.println("Total character count: " + ConcurrentFileProcessor.totalCharacterCount.get());
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
