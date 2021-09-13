package com.alexcomeau.sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.alexcomeau.networking.Message;
import com.alexcomeau.networking.Operation;
import com.alexcomeau.utils.Debug;
public class SocketManager{

    private int port;

    public SocketManager(int port){
        this.port = port;
    }

    public void Start(){
        try(ServerSocket ss = new ServerSocket(port)){
            Debug.debug("waiting for connection");
            while(!ss.isClosed()){
                Socket client = ss.accept();
                Debug.debug("accepted connection from: " + client.getRemoteSocketAddress().toString());
                Debug.debug("getting output and input streams");
                ObjectOutputStream oo = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream oi = new ObjectInputStream(client.getInputStream());
                Debug.debug("sending a test message");
                
                oo.writeObject(new Message(Operation.STATUS, new String[]{"test message",  ((Double)Math.random()).toString()}));
                client.close();

            }
        }catch(Exception e){
            Debug.debug(e.toString(), true);
            e.printStackTrace();
        }
    }

}
