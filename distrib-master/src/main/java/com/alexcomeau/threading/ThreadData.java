package com.alexcomeau.threading;

import java.util.ArrayList;
import org.apache.commons.lang3.tuple.Pair;

public class ThreadData {
    private static ArrayList<Pair<Integer, Integer>> workQueue = new ArrayList<>();

    public static synchronized boolean addWork(int top, int bottom){
        return workQueue.add(Pair.of(bottom, top));
    }

    public static synchronized int getLastTop(){
        return workQueue.get(workQueue.size() - 1).getRight();
    }

    public static synchronized Pair<Integer, Integer> getWork(){
        if(workQueue.size() < 1){
            return null;
        }
        Pair<Integer, Integer> p = workQueue.get(0);
        workQueue.remove(0);
        return p;
    }
}
