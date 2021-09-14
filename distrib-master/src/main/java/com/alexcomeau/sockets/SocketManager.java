package com.alexcomeau.sockets;


import java.net.ServerSocket;
import java.net.Socket;

import com.alexcomeau.threading.ThreadData;
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
                ThreadData.sort();
                new Thread(ch).start();
            }
        }catch(Exception e){
            Debug.debug(e.toString(), true);
            e.printStackTrace();
        }
    }

}
