package Dak.Dictionary.Net;
import java.net.ServerSocket;

import Dak.Dictionary.Dictionary;
import Dak.Dictionary.Dictionary.Entry;

public class DictionaryProtocol {
   public enum Verb {
    GET, //we would like to get the following word: contains a single word
    OK, //we can return that word, contains a length indecator for the word, the word, and the message
    NULL //we do not have that word in house, empty body
   };

   public static class Packet {
    protected Verb verb;
    protected Packet() {
    }

    public static final Packet decode(Byte [] data) {
        Byte [] local_buffer = new Byte[data.length - 1];
        for (int i  = 0 ; i < data.length-1;i++)
            local_buffer[i] = data[i+1];

        //translate the given integer byte into averb, because
        //java has sad sad enumerators compared to c++
        Verb expectVerb = null;
        int verb_int = Byte.toUnsignedInt(data[0]);
        if (verb_int < Verb.values().length) {
            expectVerb = Verb.values()[verb_int];
        }

        switch (expectVerb) {
            case OK:
                return new OKPacket(local_buffer);
            case GET:
                return new GETPacket(local_buffer);
            case NULL:
                return new NULLPacket();
        }
        return null;
    }


    /** decodes the given bytes in place and fills out the current packet object*/
    //public abstract Packet decode(byte [] b);

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
    Packet(Byte [] b) {
        //the first byte of the packet is the verb, we parse that out in this
        //function
        if (b.length > 0) {
            int verb = Byte.toUnsignedInt(b[0]);
            if (verb < Verb.values().length) {
                this.verb = Verb.values()[verb];
            }
        }
    } 
    /**gets an entry from the given entry bytes */
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

    public static class GETPacket extends Packet {
        String data = "";
        public GETPacket(String word) {
            this.verb = Verb.GET;
            data = word;
        }
        public GETPacket(Byte [] data) {
            byte [] strBuffer = new byte[data.length];
            for (int i = 0; i < strBuffer.length;i++) {
                strBuffer[i] = data[i];
            }
            this.data = new String(strBuffer);
        }
        @Override
        Byte [] localEncode() {
            //convert the string encoded data into a data type that we use
            //in the rest of the program
            byte [] local_buffer = this.data.getBytes();
            Byte [] ret_val = new Byte[local_buffer.length];
            for (int i = 0; i < ret_val.length;i++)
                ret_val[i] = local_buffer[i];
            
            return ret_val;

        }     
    }
  
    public static class NULLPacket extends Packet {
        public NULLPacket() {
            this.verb = Verb.NULL;
        }
    }
    public static class OKPacket extends Packet {
        Dictionary.Entry data;
        
        public OKPacket(Byte [] b) {
            this.verb = Verb.OK;
            data = DictionaryProtocol.Packet.decodeEntry(b);
        }
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
            return Packet.encodeEntry(data);
        }
   }
}