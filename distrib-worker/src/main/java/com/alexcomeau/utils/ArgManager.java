package com.alexcomeau.utils;

import com.alexcomeau.networking.SocketManager;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.validator.routines.InetAddressValidator;

/**
 * ArgManager
 */
public class ArgManager {

    private String[] args;

    private Options opts;

    public ArgManager(String[] args){
        this.args = args;
        opts = new Options();
        optBuilder();
        
    }

    private void optBuilder() {
        Option help = Option.builder("h")
        .longOpt("help")
        .required(false)
        .desc("display this menu").build();
        opts.addOption(help);
        Option port = Option.builder("p")
        .longOpt("port")
        .hasArg()
        .required(false)
        .desc("the port to listen on for client connections, default 49155, should be a value between 0 and 65535").build();
        opts.addOption(port);
        Option remote = Option.builder("s")
        .required(true)
        .hasArg()
        .longOpt("server")
        .desc("the ip address of the server")
        .build();
        opts.addOption(remote);
        Option verbosity = Option.builder("v")
        .longOpt("verbosity")
        .hasArg()
        .required(false)
        .desc("the current verbosity level, (none, warn, error, all) \n the default is all")
        .build();
        opts.addOption(verbosity);
    }

    public void parseArgs() throws ParseException, MissingOptionException{
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(opts, args);

        if(cmd.hasOption("help")){
            printHelp();
        }

        if(cmd.hasOption("server")){
            String temp = cmd.getOptionValue("server");
            InetAddressValidator validator = new InetAddressValidator();
            if(!validator.isValid(temp)){
                printHelp();
            }else{
                SocketManager.IP = temp;
            }

        }

        if(cmd.hasOption("port")){
            try{
                SocketManager.PORT = Integer.parseInt(cmd.getOptionValue("port"));
                if(SocketManager.PORT > 65535 | SocketManager.PORT <= 0){
                    throw new NumberFormatException();
                }
            }catch(NumberFormatException e){
                System.out.println("the port number should be an integer value between 0 and 65535");
                printHelp();
            }
        }
        
        if(cmd.hasOption("verbosity")){
            try{
                String[] cmds = new String[]{"all", "none", "warn", "error"};
                String temp = cmd.getOptionValue("verbosity");
                boolean found = false;
                for(String s : cmds){
                    if(s.equals(temp)){
                        found = !found;
                    }
                }
                if(found){
                    Debug.verbosity = cmd.getOptionValue("verbosity");
                }else{
                    throw new Error();
                }
                
            }catch(Error e){
                System.out.println(cmd.getOptionValue("verbosity") + " is not one of the accepted values for verbosity");
                printHelp();
            }
        }
    }

    public void printHelp(){
        HelpFormatter hf = new HelpFormatter();
        String header = "A distributed prime number generator", footer = "";
        hf.printHelp("distrib-master", header, opts, footer, true);
        System.exit(0);
    }

    
}
