package com.alexcomeau;

import com.alexcomeau.sockets.SocketManager;
import com.alexcomeau.utils.ArgManager;
import com.alexcomeau.utils.Debug;
import com.alexcomeau.work.InitialData;
import com.alexcomeau.work.WorkManager;

import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static int GOAL = 1000000;
    public static int START_NUM = 100;
    public static int PORT = 49155;
    public static void main( String[] args ) throws Exception
    {
        ArgManager am = new ArgManager(args);
        try{
            am.parseArgs();
        }catch(MissingArgumentException | MissingOptionException ma){
            am.printHelp();
        }catch(ParseException e){
            Debug.debug("eat my pants");
        }
        
        InitialData.genInitial();
        WorkManager.createWork(START_NUM);
        SocketManager sm = new SocketManager(PORT);
        sm.Start();


        
        
    }
}
