package com.alexcomeau.utils;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

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

    private void optBuilder(){
        Option goal = Option.builder("g")
        .longOpt("goal")
        .hasArg()
        .required(false)
        .desc("The goal top number for number generation").build();
        opts.addOption(goal);
        Option chunk_size = Option.builder("c")
        .longOpt("chunk_size")
        .hasArg()
        .required(false)
        .desc("the size of each chunk of work distributed to the workers").build();
        opts.addOption(chunk_size);
        Option port = Option.builder("p")
        .longOpt("port")
        .hasArg()
        .required(true)
        .desc("the port to listen on for client connections").build();
        opts.addOption(port);
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
        Option log_level = Option.builder("l")
        .longOpt("log_level")
        .hasArg()
        .required(false)
        .desc("the current log level, (none, warn, error, all)")
        .build();
        opts.addOption(log_level);
    }

    
}