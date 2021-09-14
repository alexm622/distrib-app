package com.alexcomeau.work;

import java.util.ArrayList;

import com.alexcomeau.threading.ThreadData;
import com.alexcomeau.threading.ThreadManager;
import com.alexcomeau.utils.Debug;

public class InitialData {

    public static final int MULTIPLY_CONSTANT = 3;
    private static final int INITIAL_POOL = (ThreadManager.NUM_WORKERS * WorkManager.CHUNK_SIZE)*MULTIPLY_CONSTANT;

    public static void genInitial(){
        ArrayList<Integer> primes = ThreadData.getPrimes();
        primes.add(2);
        Debug.debug("generating initial data");
        for(int i = 3; i <= INITIAL_POOL; i += 2){
            
            Boolean isPrime = true;
            for(int prime : primes){
                if(i % prime == 0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime){
                primes.add(i);
            }
            
        }
        Debug.debug("Found " + primes.size() + " prime numbers");
        ThreadData.setLast(INITIAL_POOL);
        ThreadData.addPrimes(primes);

    }    
}
