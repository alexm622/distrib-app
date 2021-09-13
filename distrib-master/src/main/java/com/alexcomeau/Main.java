package com.alexcomeau;

import com.alexcomeau.sockets.SocketManager;
import com.alexcomeau.work.WorkManager;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws Exception
    {
        WorkManager.createWork(1000);
        SocketManager sm = new SocketManager(1000);
        sm.Start();        
    }
}
