package com.alexcomeau.data;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

import com.alexcomeau.networking.Message;
import com.alexcomeau.networking.Operation;
import com.alexcomeau.networking.SocketManager;
import com.alexcomeau.utils.Debug;

public class Synchronization {
    public static ArrayList<Integer> primes = new ArrayList<>();

    public static void initPrimes(){
        primes.add(2);
    }
    
    private static void UpdatePrimes(ArrayList<Integer> recent_numbers){
        for(Integer i : recent_numbers){
            if(!primes.contains(i)){
                primes.add(i);
            }
        }
        Collections.sort(primes);
    }

    public static void requestPrimeUpdate(){
        try{
            Socket s = SocketManager.getSocket();
            Debug.debug("getting output and input streams");
            ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream oi = new ObjectInputStream(s.getInputStream());

            //request primes
            Message m = new Message(Operation.GETPRIMES, new ArrayList<Integer>());
            oo.writeObject(m);
            m = (Message) oi.readObject();
            UpdatePrimes(m.args);

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
        
    }

    public static void submitWork(ArrayList<Integer> work){
        try{
            Socket s = SocketManager.getSocket();
            Debug.debug("getting output and input streams");
            ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream oi = new ObjectInputStream(s.getInputStream());

            //submit primes
            Message m = new Message(Operation.SUBMITWORK, work);
            oo.writeObject(m);
            m = (Message) oi.readObject();
            Debug.debug("response to work submission: " + m.o.toString());

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
    }

    

}
