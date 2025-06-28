package com.concurrentwordcounter.runners;

import com.concurrentwordcounter.threading.ThreadDelegator;

public class HeadlessRunner {
    public static void run() {
        System.out.println("Running in headless mode");
        ThreadDelegator.delegateTasks();
    }
    
}
