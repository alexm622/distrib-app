package com.alexcomeau.threading;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.tuple.Pair;

public class ThreadData {
    private static ArrayList<Pair<Integer, Integer>> workQueue = new ArrayList<>();
    private static ArrayList<Integer> primes = new ArrayList<>();
    private static AtomicInteger last = new AtomicInteger(0);

    public static synchronized boolean addWork(Pair<Integer, Integer> work){
        return workQueue.add(work);
    }

    public static synchronized Pair<Integer, Integer> getWork(){
        if(workQueue.size() < 1){
            return null;
        }
        Pair<Integer, Integer> p = workQueue.get(0);
        workQueue.remove(0);
        return p;
    }

    public static synchronized void setLast(int new_last){
        last.set(new_last);
    }

    public static synchronized int getLast(){
        return last.get();
    }

    public static synchronized void addPrimes(ArrayList<Integer> new_primes){
        primes.addAll(new_primes);
    }

    public static synchronized ArrayList<Integer> getPrimes(){
        ArrayList<Integer> copy = primes;
        return copy;
    }
}
