package com.alexcomeau.utils;

import com.alexcomeau.Main;
import com.alexcomeau.threading.ThreadManager;
import com.alexcomeau.work.InitialData;
import com.alexcomeau.work.WorkManager;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

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
        Option goal = Option.builder("g")
        .longOpt("goal")
        .hasArg()
        .required(false)
        .desc("The goal top number for number generation \n goal should be relatively large (goal > 10000) and an integer value").build();
        opts.addOption(goal);
        Option chunk_size = Option.builder("c")
        .longOpt("chunk_size")
        .hasArg()
        .required(false)
        .desc("the size of each chunk of work distributed to the workers (default: 50)").build();
        opts.addOption(chunk_size);
        Option starting_chunk = Option.builder("s")
        .longOpt("starting_chunk")
        .hasArg()
        .required(false)
        .desc("the starting chunk of data to calculate, should be around the number of nodes*chunk size").build();
        opts.addOption(starting_chunk);
        Option num_nodes = Option.builder("n")
        .longOpt("num_nodes")
        .hasArg()
        .required(false)
        .desc("the number of nodes in the cluster (use this for better optimization)").build();
        opts.addOption(num_nodes);
        Option log_level = Option.builder("v")
        .longOpt("verbosity")
        .hasArg()
        .required(false)
        .desc("the current verbosity level, (none, warn, error, all) \n the default is all")
        .build();
        opts.addOption(log_level);
    }

    public void parseArgs() throws ParseException, MissingOptionException{
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(opts, args);

        if(cmd.hasOption("help")){
            printHelp();
        }

        if(cmd.hasOption("num_nodes")){
            try{
                ThreadManager.NUM_WORKERS = Integer.parseInt(cmd.getOptionValue("num_nodes"));
            }catch(NumberFormatException e){
                System.out.println("the number of nodes should be an integer value");
                printHelp();
            }
            Debug.debug("NUM_WORKERS set to " + cmd.getOptionValue("num_nodes"));
            
        }
        if(cmd.hasOption("port")){
            try{
                Main.PORT = Integer.parseInt(cmd.getOptionValue("port"));
                if(Main.PORT > 65535 | Main.PORT <= 0){
                    throw new NumberFormatException();
                }
            }catch(NumberFormatException e){
                System.out.println("the port number should be an integer value between 0 and 65535");
                printHelp();
            }
        }
        if(cmd.hasOption("goal")){
            try{
                Main.GOAL = Integer.parseInt(cmd.getOptionValue("goal"));
            }catch(NumberFormatException e){
                System.out.println("goal should be a relatively large (goal > 10000) integer value");
                printHelp();
            }
        }
        if(cmd.hasOption("chunk_size")){
            try{
                WorkManager.CHUNK_SIZE = Integer.parseInt(cmd.getOptionValue("chunk_size"));
            }catch(NumberFormatException e){
                System.out.println("chunk size should be an integer value");
                printHelp();
            }
        }
        if(cmd.hasOption("starting_chunk")){
            try{
                int temp = Integer.parseInt(cmd.getOptionValue("starting_chunk"));
                if(temp >= (InitialData.INITIAL_POOL/3*2)){
                    InitialData.INITIAL_POOL = temp;
                }else{
                    throw new NumberFormatException();
                }
            }catch(NumberFormatException e){
                System.out.println("the starting chunk should be an integer that is more than twice the value of (number of nodes[default is 3] * chunk size[default is 50]");
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