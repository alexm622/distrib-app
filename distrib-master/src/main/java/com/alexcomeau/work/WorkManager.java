package com.alexcomeau.work;

import com.alexcomeau.threading.ThreadData;
import com.alexcomeau.utils.Debug;

import org.apache.commons.lang3.tuple.Pair;

public class WorkManager {

    //the range/size of each chunk of work
    public static final int CHUNK_SIZE = 50;

    //create the initial pool of work
    public static void createWork(int number_of_chunks){
        int ending = ThreadData.getLast();
        int last_ending = ending;
        ending += number_of_chunks*CHUNK_SIZE;
        for(int i = 0; i < number_of_chunks; i++){
            int bottom = i*CHUNK_SIZE + last_ending;
            int top = (i+1)*CHUNK_SIZE + last_ending;
            ThreadData.addWork(Pair.of(bottom, top));
            //Debug.debug("added work pair (" + bottom + "," + top + ")" + " " + i+1 + " of " + number_of_chunks);
        }
        ThreadData.setLast(ending);
    }

    //add work to the work queue
    public static void addWork(int count, String preapp){
        int ending = ThreadData.getLast();
        for(int i = 0; i < count; i++){
            int bottom = ending+i*CHUNK_SIZE;
            int top = ending+(i+1)*CHUNK_SIZE;
            ThreadData.addWork(Pair.of(bottom, top));
            Debug.debug("added work pair (" + bottom + "," + top + ")" + " " + i+1 + " of " + count, preapp);
        }
        ending += count*CHUNK_SIZE;
        ThreadData.setLast(ending);
    }
    
}
