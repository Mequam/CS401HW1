package Dak.Dictionary.Net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


import Dak.Dictionary.DictionaryLL;
import Dak.Dictionary.Dictionary.Entry;
import Dak.Dictionary.Net.DictionaryProtocol.*;

/** 
 * this class serves a SINGLE dictionary client using the protocol
 * it is inteanded to be instanceds as a thread by a parent class
 * 
*/
public class DictionaryServer extends Thread {
    DataInputStream link;
    DataOutputStream link_out;

    DictionaryLL dictionary;


    DictionaryServer(Socket s,File f) {
        dictionary = new DictionaryLL(f);
        load_socket(s);
    }


    DictionaryServer(Socket s,String dictionary_fpath) {
        dictionary = new DictionaryLL(dictionary_fpath);
        load_socket(s);
    }

    private void load_socket(Socket s) {
        try {
            this.link = new DataInputStream(s.getInputStream());
            this.link_out = new DataOutputStream(s.getOutputStream());
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
                if (p.verb == Verb.GET) {
                        String test = ((GETPacket)p).data;
                        Entry def = this.dictionary.getEntry(test);

                    if (def == null) { //tell the client their word does not exist
                        link_out.write(
                            Packet.unbox(new NULLPacket().encode())
                        );
                    }
                    else {
                        link_out.write(
                            Packet.unbox(new OKPacket(def).encode())
                        );
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
        }
    }
}
