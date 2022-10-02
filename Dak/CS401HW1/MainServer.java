package Dak.CS401HW1;

import java.net.ServerSocket;

import Dak.Dictionary.DictionaryLL;
//contains the code to handle a SINGLE client in a thread
import Dak.Dictionary.Net.DictionaryServer;

import java.net.*;

public class MainServer {
    public static void main(String [] args) {
        String fpath = "Dictionary.txt";
        if (args.length > 0) {
            fpath = args[0];
        }

        int port = 8000;
        
        DictionaryLL dll = new DictionaryLL(fpath);
        System.out.println("[*] reading from dictionary file: " + fpath);

        try {
       
        ServerSocket s = new ServerSocket(port);
        System.out.println("[*] Starting on (0.0.0.0:" + Integer.toString(port) + ")");
        
        while (true) {
            //start a new thread (which will terminate on disconnect)
            //foreach client that joins the connection
            new DictionaryServer(s.accept(),dll).run(); 
        }
    }
    catch (Exception e) {
        System.out.println(e);
        System.exit(0);
    }
}
}
