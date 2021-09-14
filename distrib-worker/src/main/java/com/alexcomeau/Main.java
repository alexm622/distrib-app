package com.alexcomeau;

import java.util.ArrayList;

import com.alexcomeau.data.Synchronization;
import com.alexcomeau.math.Worker;
import com.alexcomeau.networking.SocketManager;

/**
 * Hello world!
 *
 */
public class Main 
{
    private static boolean runme = true;
    public static void main( String[] args ) throws Exception
    {
        //main program loop
        ArrayList<Integer> work = new ArrayList<>();
        while(runme){
            Synchronization.requestPrimeUpdate();
            Synchronization.submitWork(work);
            if(SocketManager.isDone()){
                break;
            }
            work = SocketManager.getWork();
            work = Worker.Work(work);

        }
    }
}
