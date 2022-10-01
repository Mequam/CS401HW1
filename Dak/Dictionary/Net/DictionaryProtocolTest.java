package Dak.Dictionary.Net;

import Dak.Dictionary.*;
import Dak.Dictionary.Net.DictionaryProtocol.OKPacket;
import Dak.Dictionary.Net.DictionaryProtocol.ProtocolPacket;


public class DictionaryProtocolTest {
    public static void main(String [] s) {
        System.out.println(
        DictionaryProtocol.ProtocolPacket.decodeEntry(
            DictionaryProtocol.ProtocolPacket.encodeEntry(
                new Dictionary.Entry("Up","the sqrt(-1)")))
            );

        OKPacket p = (OKPacket) ProtocolPacket.decode(
            new OKPacket(
                new Dictionary.Entry("sin",
                "the vertical distance during circular motion,or a non godly act :)")
                ).encode()
        );
        System.out.println(p.data);
    }
}
