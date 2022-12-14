package Dak.Dictionary.Net;

import Dak.Dictionary.*;
import Dak.Dictionary.Net.DictionaryProtocol.GETPacket;
import Dak.Dictionary.Net.DictionaryProtocol.NULLPacket;
import Dak.Dictionary.Net.DictionaryProtocol.OKPacket;
import Dak.Dictionary.Net.DictionaryProtocol.Packet;


public class DictionaryProtocolTest {
    public static void main(String [] s) {
        System.out.println(
        DictionaryProtocol.Packet.decodeEntry(
            DictionaryProtocol.Packet.encodeEntry(
                new Dictionary.Entry("Up","the sqrt(-1)")))
            );

        OKPacket p = (OKPacket) Packet.decode(
            new OKPacket(
                new Dictionary.Entry("sin",
                "the vertical distance during circular motion,or a non godly act :)")
                ).encode()
        );
        System.out.println(p.data);
        
        GETPacket g = (GETPacket)Packet.decode(new GETPacket("sin").encode());
        System.out.println(g.data);

        NULLPacket n = (NULLPacket)Packet.decode(new NULLPacket().encode());
        System.out.println(n.verb);
    }
}
