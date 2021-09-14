package com.alexcomeau.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.alexcomeau.utils.Debug;

public class SocketManager {

    public static Socket getSocket() throws UnknownHostException, IOException{
        Socket s = new Socket("127.0.0.1", 1000);
        if(s.isConnected()){
            //Debug.debug("connection accepted");  
        }
        return s;
    }

    public static ArrayList<Integer> getWork(){
        ArrayList<Integer> work = new ArrayList<>();
        try{
            Socket s = SocketManager.getSocket();
            //Debug.debug("getting output and input streams");
            ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream oi = new ObjectInputStream(s.getInputStream());

            //submit primes
            Message m = new Message(Operation.GETWORK, new ArrayList<Integer>());
            oo.writeObject(m);
            m = (Message) oi.readObject();
            //Debug.debug("response to request for work: " + m.o.toString());
            work = m.args;

            //tell the master that this node is disconnecting
            m = new Message(Operation.CLOSE, new ArrayList<>());
            oo.writeObject(m);
            m = (Message) oi.readObject();

            //close streams
            oi.close();
            oo.close();
            s.close();
        }catch(Exception e){
            Debug.debug(e.toString(), true);
            e.printStackTrace();
        }
        return work;
    }

    public static boolean isDone(){
        Operation o = Operation.CONTINUE;
        try{
            Socket s = SocketManager.getSocket();
            //Debug.debug("getting output and input streams");
            ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream oi = new ObjectInputStream(s.getInputStream());

            //submit primes
            Message m = new Message(Operation.STATUS, new ArrayList<Integer>());
            oo.writeObject(m);
            m = (Message) oi.readObject();
            //Debug.debug("response to request for status: " + m.o.toString());
            o = m.o;

            //tell the master that this node is disconnecting
            m = new Message(Operation.CLOSE, new ArrayList<>());
            oo.writeObject(m);
            m = (Message) oi.readObject();

            //close streams
            oi.close();
            oo.close();
            s.close();
        }catch(Exception e){
            Debug.debug(e.toString(), true);
            e.printStackTrace();
        }
        return (o == Operation.DONE);
    }
    
}
