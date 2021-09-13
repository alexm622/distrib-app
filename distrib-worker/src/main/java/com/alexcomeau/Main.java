package com.alexcomeau;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.alexcomeau.networking.Message;
import com.alexcomeau.networking.Operation;
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
        
        Message send_me = new Message(Operation.GETWORK, new ArrayList<>());
        oo.writeObject(send_me);


        Debug.debug("reading message");
        Message m = (Message) oi.readObject();
        Debug.debug("message type is: " + m.o.toString());
        int temp = 0;
        for(int i : m.args){
            temp++;
            Debug.debug("arg " + temp + " is " + i);
        }
        send_me = new Message(Operation.CLOSE, new ArrayList<>());
        oo.writeObject(send_me);
        m = (Message) oi.readObject();
        s.close();
    }
}
