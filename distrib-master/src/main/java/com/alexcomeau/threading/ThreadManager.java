package com.alexcomeau.threading;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadManager {
    private static AtomicInteger numThreads = new AtomicInteger(0);

    public static synchronized int getNumThreads(){
        return numThreads.get();
    }

    public static synchronized int incThreadCount(){
        return numThreads.incrementAndGet();
    }
    
}