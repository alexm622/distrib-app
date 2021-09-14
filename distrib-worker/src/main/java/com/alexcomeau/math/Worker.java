package com.alexcomeau.math;

import java.util.ArrayList;

import com.alexcomeau.data.Synchronization;

public class Worker {

    public static ArrayList<Integer> Work(ArrayList<Integer> work){
        int bottom = work.get(0);
        int top = work.get(1);
        ArrayList<Integer> mywork = new ArrayList<>();
        for(int i = bottom; i <= top; i += 2){
            
            Boolean isPrime = true;
            for(int prime : Synchronization.primes){
                if(i % prime == 0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime){
                mywork.add(i);
                Synchronization.primes.add(i);
            }
            
        }
        return mywork;
    }
    
    
}
