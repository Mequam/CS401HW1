package Dak.Dictionary.Net;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

import javax.swing.plaf.synth.SynthDesktopIconUI;

import Dak.Dictionary.*;
import Dak.Dictionary.Dictionary.Entry;
import Dak.Dictionary.Net.DictionaryProtocol.Verb;
public class DictionaryNetClient extends Dictionary {
    InputStream in_link;
    OutputStream out_link;        

    private void load(Socket s) {
        try {
        in_link = s.getInputStream();
        out_link = s.getOutputStream();
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(-1); //sometimes, I really just want the program to crash
        }

    }

    public DictionaryNetClient(SocketAddress sa) {
        Socket s =  new Socket();
       
        try {
            s.connect(sa);
        }
        catch (Exception e) {
            System.out.println("[ERROR] unable to connect to dictionary!!");
            System.out.println("[HINT] is the dictionary server running?");
            System.exit(0);
        }
        
        load(s);
    }

    public DictionaryNetClient(Socket s) {
        load(s);
    }
    /** 
     * requests we add a new entry e,
     * this is not yet implimented for the client
     * 
    */
    @Override
    public void addEntry(Entry e) {

    }
    /**gets a given entry from the server*/
    @Override
    public Entry getEntry(String word) {
        try { //ugly java try catch is ugly :(

            Byte [] data = (new DictionaryProtocol.GETPacket(word)).encode();
            out_link.write(
                DictionaryProtocol.Packet.unbox(data)
            );
        }
        catch (Exception e ) {
            System.out.println(e);
            System.exit(0);
        }

        byte [] responce_buffer = new byte[255];

        try {
            int amount_read = in_link.read(responce_buffer);
            Byte [] packet_bytes = DictionaryProtocol.Packet.box(responce_buffer,amount_read);
            DictionaryProtocol.Packet p =  DictionaryProtocol.Packet.decode(packet_bytes);

            if (p.verb == Verb.OK)
                return ((DictionaryProtocol.OKPacket)p).data;

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        
        return null;
    }
}
