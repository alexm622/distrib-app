package com.alexcomeau.networking;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 123645;
    public Operation o;
    public ArrayList<Integer> args;
    public Message(Operation o,  ArrayList<Integer> args){
        this.o = o;
        this.args = args;
    }

}
