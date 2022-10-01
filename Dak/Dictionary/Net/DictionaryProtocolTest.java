package Dak.Dictionary.Net;

import Dak.Dictionary.*;


public class DictionaryProtocolTest {
    public static void main(String [] s) {
        System.out.println(
        DictionaryProtocol.ProtocolPacket.decodeEntry(
            DictionaryProtocol.ProtocolPacket.encodeEntry(
                new Dictionary.Entry("Up","the sqrt(-1)")))
            );
    }
}
