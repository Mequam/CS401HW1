package Dak.Dictionary.Net;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

public class DictionaryNetClientTest {
        public static void main(String [] args) {
            DictionaryNetClient dnc = new DictionaryNetClient(new InetSocketAddress("localhost",2644)); 
            dnc.getEntry("test");
        }
}
