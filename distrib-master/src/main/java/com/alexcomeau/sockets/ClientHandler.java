package com.alexcomeau.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.alexcomeau.networking.Message;
import com.alexcomeau.networking.Operation;
import com.alexcomeau.threading.ThreadData;
import com.alexcomeau.utils.Debug;
import com.alexcomeau.work.WorkManager;

import org.apache.commons.lang3.tuple.Pair;

public class ClientHandler implements Runnable{

    private Socket socket;
    private int jobNumber;
    private String preApp;

    private ObjectInputStream oi;
    private ObjectOutputStream oo;

    public ClientHandler(Socket socket, int jobNumber){
        this.socket = socket;
        this.jobNumber = jobNumber;
        preApp = "JobNumber-" + this.jobNumber + " Ip-" + socket.getRemoteSocketAddress().toString() + ": ";
    }

    public void run(){
        try{
            Debug.debug("getting output and input streams", preApp, false);
            oo = new ObjectOutputStream(socket.getOutputStream());
            oi = new ObjectInputStream(socket.getInputStream());
            
            Message m = (Message) oi.readObject();
            handleOperation(m);

            socket.close();
        }catch(Exception e){
            Debug.debug(e.toString(), true);
            e.printStackTrace();
        }

    }

    private void handleOperation(Message m) throws IOException, ClassNotFoundException{
        Message new_m;
        switch(m.o){
            case NEGOTIATE:
                //do negotiation things
                break;
            case GETWORK:
                //return work
                Pair<Integer, Integer> p = ThreadData.getWork();
                WorkManager.addWork(1, preApp);
                ArrayList<Integer> work = new ArrayList<>();
                work.add(p.getLeft());
                work.add(p.getRight());
                Debug.debug("sending work to worker", preApp);
                new_m = new Message(Operation.WORK, work);
                oo.writeObject(new_m);
                m = (Message) oi.readObject();
                handleOperation(m);
                break;
            case SUBMITWORK:
                //recieve work
                Debug.debug("recieving work from worker", preApp);
                ThreadData.addPrimes(m.args);
                new_m = new Message(Operation.ACK, new ArrayList<>());
                oo.writeObject(new_m);
                m = (Message) oi.readObject();
                handleOperation(m);
                break;
            case GETPRIMES:
                //submit primes
                ArrayList<Integer> primes = ThreadData.getPrimes();
                Debug.debug("Sending primes to worker", preApp);
                ArrayList<Integer> toSend = new ArrayList<>();
                int size = primes.size();
                int bottom_size = size - 50;
                if(bottom_size < 0){
                    bottom_size = 0;
                }
                for(int i = bottom_size; i < size; i++){
                    toSend.add(primes.get(i));
                }
                new_m = new Message(Operation.PRIMES, toSend);
                oo.writeObject(new_m);
                m = (Message) oi.readObject();
                handleOperation(m);
                break;
            case CLOSE:
                Debug.debug("closing worker", preApp);
                new_m = new Message(Operation.CLOSE, new ArrayList<>());
                oo.writeObject(new_m);
                break;
            default:
                Debug.debug("got incorrect/invalid operation from client", this.preApp, true);
        }
    }
    
}
