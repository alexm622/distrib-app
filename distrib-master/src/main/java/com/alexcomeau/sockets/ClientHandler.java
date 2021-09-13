package com.alexcomeau.sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.alexcomeau.networking.Message;
import com.alexcomeau.networking.Operation;
import com.alexcomeau.utils.Debug;

public class ClientHandler implements Runnable{

    private Socket socket;
    private int jobNumber;
    private String preApp;

    public ClientHandler(Socket socket, int jobNumber){
        this.socket = socket;
        this.jobNumber = jobNumber;
        preApp = "JobNumber-" + this.jobNumber + " Ip-" + socket.getRemoteSocketAddress().toString() + ": ";
    }

    public void run(){
        try{
            Debug.debug("getting output and input streams", preApp, false);
            ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream oi = new ObjectInputStream(socket.getInputStream());
            

            oo.writeObject(new Message(Operation.STATUS, new String[]{"test message",  ((Double)Math.random()).toString()}));
            socket.close();
        }catch(Exception e){
            Debug.debug(e.toString(), true);
            e.printStackTrace();
        }

    }
    
}
