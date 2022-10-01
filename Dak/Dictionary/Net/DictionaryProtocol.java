package Dak.Dictionary.Net;
import java.net.ServerSocket;

import Dak.Dictionary.Dictionary;
import Dak.Dictionary.Dictionary.Entry;

public class DictionaryProtocol {
   enum Verb {
    GET,
    OK,
    NULL
   };

   public class ProtocolPacket {
    protected Verb verb;
    protected ProtocolPacket() {
    }

    /** decodes the given bytes in place and fills out the current packet object*/
    //public abstract ProtocolPacket decode(byte [] b);

    /** encodes this packet */
    public Byte [] encode() {
        Byte [] enc_buffer = localEncode();
        Byte [] ret_val = new Byte[enc_buffer.length + 1];

        
        ret_val[0] = (byte)(verb.ordinal());

        for (int i = 0; i < enc_buffer.length;i++)
            ret_val[i + 1] = enc_buffer[i];
        
        return ret_val;
    }    

    /** 
     * different packet types overload this class to encode their data
     * and then this packet super type wraps that data in its encode function
     * while attaching additional data to be sent over the network (e.g. verb)
     */
    Byte [] localEncode() {
        return new Byte [0];
    }


    /** generate a new protocol packet for the given byte data */
    ProtocolPacket(Byte [] b) {
        //the first byte of the packet is the verb, we parse that out in this
        //function
        if (b.length > 0) {
            int verb = Byte.toUnsignedInt(b[0]);
            if (verb < Verb.values().length) {
                this.verb = Verb.values()[verb];
            }
        }
    } 
    public static Entry decodeEntry(Byte [] b) { 
        //get the length of the given word
        int word_size = Byte.toUnsignedInt(b[0]);
        
        //copy over the word from the packet
        byte [] word_buffer = new byte[word_size];
        for (int i = 0; i < word_buffer.length;i++)
            word_buffer[i] = b[i+1];

        //copy over the definition from the packet
        byte [] definition_buffer = new byte[b.length - 1 - word_size];
        for (int i = 0; i < definition_buffer.length; i++)
            definition_buffer[i] = b[i+1+word_buffer.length];

        //return a packaged entry generated from the given data
        return new Entry(new String(word_buffer),new String(definition_buffer));

    }
    public static Byte [] encodeEntry(Entry e) {
        Byte [] ret_val = new Byte[e.length() + 1];  //one extra byte for word size

        byte [] wordArr = e.word.getBytes();
        //copy the word into the return buffer
        for (int i = 0;i < wordArr.length; i++) {
            ret_val[i+1] = wordArr[i];
        }

        byte [] definitionArr = e.definition.getBytes();
        //copy the definition array into the return buffer
        for (int i = 0; i < definitionArr.length;i++) {
            ret_val[i + wordArr.length + 1] = definitionArr[i];
        }
        
        ret_val[0] = (byte) wordArr.length;
        
        return ret_val;
    }    
   }

   public class OKPacket extends ProtocolPacket {
        Dictionary.Entry data;
        public OKPacket(Dictionary.Entry e) {
            this.verb = Verb.OK;
            this.data = e;
        }
        /**
         * to encode an ok packet, we send over the data corrisponding to
         * the entry
        */
        @Override
        Byte [] localEncode() {
            return ProtocolPacket.encodeEntry(data);
        }
   }
}