package com.alexcomeau;

import java.net.ServerSocket;
import java.net.Socket;

import com.alexcomeau.sockets.SocketManager;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws Exception
    {
        SocketManager sm = new SocketManager(1000);
        sm.Start();        
    }
}
