package com.concurrentfileprocessor.runners;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        outputFilePath = currentDir;
        outputFilename = getOutputFilename();

        File inputDirectory = new File(currentDir);
        if (inputDirectory.exists() && inputDirectory.isDirectory()) {
            inputFiles = getInputFiles(inputDirectory);
            if (inputFiles.isEmpty()) {
                System.out.println("No text files found in current directory, ending processing");
            } else{
                processAndDisplayResults();
            }
        }
    }

    private static void processAndDisplayResults() {
        FileProcessor.processFiles();
        System.out.println("Total file count: " + fileStats.numberOfFiles);
        System.out.println("Total character count: " + fileStats.characterCount.get());
        System.out.println("Total line count: " + fileStats.lineCount.get());
        System.out.println("Total word count: " + fileStats.wordCount.size());
    }

    private static List<File> getInputFiles(File directory){
        ArrayList<File> filesArray = new ArrayList<>();
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            if (files != null) {
                filesArray.addAll(Arrays.asList(files));
                System.out.println("Loaded " + filesArray.size() + " input files");
            }
        return filesArray;
    }
    
    private static String getOutputFilename() {
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
