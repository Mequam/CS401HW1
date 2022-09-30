package Dak.Dictionary;
import LinkedListTemplate.*;
import java.io.*;
import java.util.Scanner;

public class DictionaryLL extends Dictionary {
    private LinkedList<Entry> l = new LinkedList<Entry>();

    public DictionaryLL(String f) {
        File nf = new File(f);
        load(nf);
    }

    private void load(File f) {
        Scanner fc = null;
        try {
            fc = new Scanner(f);
            while (fc.hasNextLine()) {
                String entry = fc.nextLine();
                if (!entry.equals(""))
                    l.addFirst(new Entry(entry));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            //make sure to close the scanner after usage
            if (fc != null) {
                fc.close();
            }
        }
    }
    public DictionaryLL(File f) {
        load(f);
    }
    @Override
    public Entry getEntry(String word) {
            return new Entry("null","");
    }
    @Override
    public void addEntry(Entry entry) { 
        l.forEach((n)->{
            if (n.getElement().equals(entry)) {
                return false;
            }
            return true;
        });
        l.addFirst(entry);
    }
}
