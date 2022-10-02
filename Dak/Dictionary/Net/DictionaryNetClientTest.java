package Dak.Dictionary.Net;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.util.*;

import Dak.Dictionary.Dictionary.Entry;

public class DictionaryNetClientTest {
        public static void main(String [] args) {
            String query_word = "";
            if (args.length > 0) {
                query_word = args[0];
            }
            else {
                Scanner sc = new Scanner(System.in);
                query_word = sc.nextLine();
            }

            DictionaryNetClient dnc = new DictionaryNetClient(new InetSocketAddress("localhost",8000)); 
            
            Entry ent = dnc.getEntry(query_word);

            if (ent != null) {
                System.out.println(ent.definition);
            }
            else {
                System.out.println("[*] " + query_word + " is not in the dictionary");
            }
        }
}
