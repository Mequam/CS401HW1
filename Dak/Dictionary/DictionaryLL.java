package Dak.Dictionary;
import LinkedListTemplate.*;
import java.io.*;
import java.util.Scanner;

public class DictionaryLL extends Dictionary {
    private LinkedList<Entry> l;
    public DictionaryLL(File f) {
        try {
            Scanner fc = new Scanner(f);
            while (fc.hasNextLine()) {
                String entry = fc.nextLine();
                l.addFirst(new Entry(entry));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    @Override
    public Entry getEntry(String word) {
            return new Entry("null","");
    }
}
