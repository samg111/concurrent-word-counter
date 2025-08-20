package com.concurrentfileprocessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.concurrentfileprocessor.runners.GuiRunner;
import com.concurrentfileprocessor.runners.HeadlessRunner;

public class ConcurrentFileProcessor {
    public static FileStats fileStats;
    public static List<File> inputFiles;
    public static String outputFilename;
    public static String outputFilePath;
    
    public static void main(String[] args) {
        fileStats = new FileStats();
        fileStats = fileStats.refreshFileStats(fileStats);
        initFileDetails();
        if (args.length > 0 && args[0].equals("--headless")) {
            HeadlessRunner.run();
        } else {
            GuiRunner.launch();
        }
    }

    public static void initFileDetails(){
        inputFiles = new ArrayList<>();
        outputFilename = "processed_file_stats.txt";
        outputFilePath = getDefaultDownloadsPath();
    }

    public static String getDefaultDownloadsPath() {
        String home = System.getProperty("user.home");
        String downloads = home + File.separator + "Downloads";
        File downloadsDir = new File(downloads);

        return downloadsDir.exists() ? downloads : home;
    }
}
