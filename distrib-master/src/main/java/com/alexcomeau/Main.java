package com.alexcomeau;

import com.alexcomeau.sockets.SocketManager;
import com.alexcomeau.work.InitialData;
import com.alexcomeau.work.WorkManager;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static final int GOAL = 6000;
    public static final int START_NUM = 100;
    public static void main( String[] args ) throws Exception
    {
        InitialData.genInitial();
        WorkManager.createWork(START_NUM);
        SocketManager sm = new SocketManager(1000);
        sm.Start();


        
        
    }
}
