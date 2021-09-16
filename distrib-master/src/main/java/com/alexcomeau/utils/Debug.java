package com.alexcomeau.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Debug{
    private static final String logs = "logs/";
    private static final Date date = new Date();
    public static String verbosity = "All";
    public static void debug(Object msg, boolean err) {
        try {
            //get the date for the name of the file
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

            //new output writer into the new log file
            PrintWriter out = new PrintWriter(new FileWriter(
                     logs + dateFormat.format(date) + ".log", true));
            dateFormat = new SimpleDateFormat("[yyyy:MM:dd HH:mm:ss] -- ");
            //get the full working class name
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            //get the name of the class
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            //get the method that called debug
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            //the line number of what called debug
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            //and the current date
            String temp = dateFormat.format(new Date());
            //add the classname, method name, line number and msg
            temp += className;
            temp += "." + methodName + "():";
            temp += lineNumber + ": ";
            temp += msg;
            temp += " \n";
            //if it is an error separate each one with pound instead of hyphens
            String sep = "----------------------------------";
            if(err){
                sep = "#############################";
            }
            //print a blank name
            System.out.println();
            //print out the error message
            System.out.println(temp);


            out.println();
            out.println();
            out.println(sep);

            out.println(temp);

            out.println(sep);
            out.close();
        } catch (IOException e) {
            File f = new File(logs);
            boolean result = f.mkdirs();
            debug("attempting to create logs folder to fix issue. was it the issue: " + result);

        }
    }
    public static void debug(Object msg, String preApp, boolean err) {
        try {
            //get the date for the name of the file
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

            //new output writer into the new log file
            PrintWriter out = new PrintWriter(new FileWriter(
                     logs + dateFormat.format(date) + ".log", true));
            dateFormat = new SimpleDateFormat("[yyyy:MM:dd HH:mm:ss] -- ");
            //get the full working class name
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            //get the name of the class
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            //get the method that called debug
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            //the line number of what called debug
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            //and the current date
            String temp = dateFormat.format(new Date());
            //add the classname, method name, line number and msg
            temp += preApp;
            temp += className;
            temp += "." + methodName + "():";
            temp += lineNumber + ": ";
            temp += msg;
            temp += " \n";
            //if it is an error separate each one with pound instead of hyphens
            String sep = "----------------------------------";
            if(err){
                sep = "#############################";
            }
            //print a blank name
            System.out.println();
            //print out the error message
            System.out.println(temp);


            out.println();
            out.println();
            out.println(sep);

            out.println(temp);

            out.println(sep);
            out.close();
        } catch (IOException e) {
            File f = new File(logs);
            boolean result = f.mkdirs();
            debug("attempting to create logs folder to fix issue. was it the issue: " + result);

        }
    }
    public static void debug(Object msg, String preApp) {
        try {
            //get the date for the name of the file
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

            //new output writer into the new log file
            PrintWriter out = new PrintWriter(new FileWriter(
                     logs + dateFormat.format(date) + ".log", true));
            dateFormat = new SimpleDateFormat("[yyyy:MM:dd HH:mm:ss] -- ");
            //get the full working class name
            String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
            //get the name of the class
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            //get the method that called debug
            String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
            //the line number of what called debug
            int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
            //and the current date
            String temp = dateFormat.format(new Date());
            //add the classname, method name, line number and msg
            temp += preApp;
            temp += className;
            temp += "." + methodName + "():";
            temp += lineNumber + ": ";
            temp += msg;
            temp += " \n";
            //if it is an error separate each one with pound instead of hyphens
            String sep = "----------------------------------";
            
            //print a blank name
            System.out.println();
            //print out the error message
            System.out.println(temp);


            out.println();
            out.println();
            out.println(sep);

            out.println(temp);

            out.println(sep);
            out.close();
        } catch (IOException e) {
            File f = new File(logs);
            boolean result = f.mkdirs();
            debug("attempting to create logs folder to fix issue. was it the issue: " + result);

        }
    }

    public static void debug(Object msg){
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

                PrintWriter out = new PrintWriter(new FileWriter(
                        logs + dateFormat.format(date) + ".log", true));
                dateFormat = new SimpleDateFormat("[yyyy:MM:dd HH:mm:ss] -- ");
                String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
                String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
                String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
                int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
                String temp = dateFormat.format(new Date());
                temp += className;
                temp += "." + methodName + "():";
                temp += lineNumber + ": ";
                temp += msg;
                temp += " \n";
                String sep = "----------------------------------";

                System.out.println();

                System.out.println(temp);


                out.println();
                out.println();
                out.println(sep);

                out.println(temp);

                out.println(sep);
                out.close();
            } catch (IOException e) {
                File f = new File(logs);
                boolean result = f.mkdirs();
                debug("attempting to create logs folder to fix issue. was it the issue: " + result);
            }
        }
}