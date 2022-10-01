package Dak.Dictionary.Net;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

public class DictionaryNetTest {
    public static void main(String [] args) {
        try {
            ServerSocket s = new ServerSocket(2644);
            
            DictionaryServer ds = new DictionaryServer(s.accept(),"/home/j0hn/ProgramingWorkshop/java/dictionary_server/Dictionary.txt");
            
            ds.start(); //actually start the server
        }
        catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
