package com.alexcomeau.sockets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.alexcomeau.networking.Message;
import com.alexcomeau.networking.Operation;
import com.alexcomeau.threading.ThreadManager;
import com.alexcomeau.utils.Debug;
public class SocketManager{

    private int port;

    public SocketManager(int port){
        this.port = port;
    }

    public void Start(){
        try(ServerSocket ss = new ServerSocket(port)){
            Debug.debug("waiting for connection");
            while(true){
                Socket client = ss.accept();
                Debug.debug("accepted connection from: " + client.getRemoteSocketAddress().toString());
                ClientHandler ch = new ClientHandler(client, ThreadManager.incThreadCount());

                new Thread(ch).start();
            }
        }catch(Exception e){
            Debug.debug(e.toString(), true);
            e.printStackTrace();
        }
    }

}
