package com.alexcomeau;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.alexcomeau.networking.Message;
import com.alexcomeau.utils.Debug;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws Exception
    {
        Socket s = new Socket("127.0.0.1", 1000);
        if(s.isConnected()){
            Debug.debug("connection accepted");    
        }
        Debug.debug("getting output and input streams");
        ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream oi = new ObjectInputStream(s.getInputStream());
        
        Debug.debug("reading message");
        Message m = (Message) oi.readObject();
        Debug.debug("message type is: " + m.o.toString());
        int temp = 0;
        for(String str : m.args){
            temp++;
            Debug.debug("arg " + temp + " is " + str);
        }
        s.close();
    }
}
