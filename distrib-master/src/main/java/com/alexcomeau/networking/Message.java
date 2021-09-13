package com.alexcomeau.networking;

import java.io.Serializable;

public class Message implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 123645;
    public Operation o;
    public String[] args;
    public Message(Operation o, String[] args){
        this.o = o;
        this.args = args;
    }

}
