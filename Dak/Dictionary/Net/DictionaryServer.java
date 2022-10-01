package Dak.Dictionary.Net;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Dak.Dictionary.Net.DictionaryProtocol.Packet;

/** 
 * this class serves a SINGLE dictionary client using the protocol
 * it is inteanded to be instanceds as a thread by a parent class
 * 
*/
public class DictionaryServer extends Thread {
    DataInputStream link;
    
    DictionaryServer(Socket s) {
        try {
            this.link = new DataInputStream(s.getInputStream());
        }
        catch (Exception e) {
            System.out.print(e);
            System.exit(0);
        }

    }

    public void run() {
        byte [] connection_buffer = new byte[255];
        while (true){ //TODO: figure out an exit condition for this
            try {
                int amount = link.read(connection_buffer);
                Packet p = DictionaryProtocol.Packet.decode(connection_buffer);
                System.out.println(p.verb);

            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
        }
    }
}
